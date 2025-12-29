package com.rgcastrof.trustcam.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.rgcastrof.trustcam.ui.composables.CameraPreview
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.layout.Column
import androidx.core.content.ContextCompat
import com.rgcastrof.trustcam.ui.composables.CameraControls
import com.rgcastrof.trustcam.uistate.CameraUiState
import java.io.File

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CameraScreen(
    uiState: CameraUiState,
    onPhotoSaved: (String) -> Unit,
    onSwitchCamera: () -> Unit,
    onNavigateToGallery: () -> Unit,
    context: Context
) {
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        controller.cameraSelector = uiState.cameraSelector
        CameraPreview(
            controller = controller,
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(9f/16f)
        )

        CameraControls(
            onOpenGallery = {
                onNavigateToGallery()
            },
            onTakePhoto = {
                takePhoto(
                    context = context,
                    controller = controller,
                    onPhotoCaptured = { uriString ->
                        onPhotoSaved(uriString)
                        Toast.makeText(context, "Photo taken!", Toast.LENGTH_SHORT).show()
                    }
                )
            },
            onSwitchCamera = onSwitchCamera,
        )
    }
}

private fun takePhoto(
    context: Context,
    controller: LifecycleCameraController,
    onPhotoCaptured: (String) -> Unit
) {
    val photosDir = File(context.filesDir, "my_images")
    if (!photosDir.exists()) photosDir.mkdirs()
    val photoFile = File(
        photosDir,
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
                onPhotoCaptured(photoFile.absolutePath)
            }
        }
    )
}
