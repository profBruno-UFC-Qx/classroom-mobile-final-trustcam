package com.rgcastrof.trustcam.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rgcastrof.trustcam.ui.composables.CameraPreview
import com.rgcastrof.trustcam.ui.theme.TrustCamTheme
import com.rgcastrof.trustcam.viewmodel.CameraViewModel
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.ui.Alignment
import androidx.core.content.ContextCompat
import com.rgcastrof.trustcam.ui.composables.CameraControls
import java.io.File

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CameraScreen(context: Context) {
    val viewModel: CameraViewModel = viewModel(factory = CameraViewModel.Factory)
    TrustCamTheme {
        val scaffoldState = rememberBottomSheetScaffoldState()
        val controller = remember {
            LifecycleCameraController(context).apply {
                setEnabledUseCases(
                    CameraController.IMAGE_CAPTURE or
                            CameraController.VIDEO_CAPTURE
                )
            }
        }
        val uiState by viewModel.uiState.collectAsState()

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetContent = {

            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                controller.cameraSelector = uiState.cameraSelector
                CameraPreview(
                    controller = controller,
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(9f/16f)
                        .align(Alignment.TopCenter)
                )

                CameraControls(
                    onOpenGallery = {
                        // TODO: Implement integrated gallery
                    },
                    onTakePhoto = {
                        takePhoto(
                            context = context,
                            controller = controller,
                            onPhotoSaved = { uriString ->
                                viewModel.storePhotoInDevice(uriString)
                                Toast.makeText(context, "Photo taken!", Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    onSwitchCamera = viewModel::switchCamera,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

private fun takePhoto(
    context: Context,
    controller: LifecycleCameraController,
    onPhotoSaved: (String) -> Unit
) {
    val photoFile = File(
        context.filesDir,
        "trustcam_${System.currentTimeMillis()}.jpg"
    )
    val outputFileOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    controller.takePicture(
        outputFileOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                Log.e("Camera", "Couldn't take photo: ", exception)
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = outputFileResults.savedUri
                onPhotoSaved(savedUri.toString())
            }
        }
    )
}
