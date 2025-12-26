package com.rgcastrof.trustcam

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rgcastrof.trustcam.ui.screens.CameraScreen
import com.rgcastrof.trustcam.ui.screens.GalleryScreen
import com.rgcastrof.trustcam.viewmodel.CameraViewModel

sealed class Screen(val route: String) {
    object CameraScreen : Screen("camera_screen")
    object GalleryScreen : Screen("gallery_screen")
}

@Composable
fun TrustCamNavigation(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CameraScreen.route) {
        composable(route = Screen.CameraScreen.route) {
            val viewModel: CameraViewModel = viewModel(factory = CameraViewModel.Factory)
            val uiState by viewModel.uiState.collectAsState()
            CameraScreen(
                uiState = uiState,
                onSwitchCamera = viewModel::switchCamera,
                onPhotoSaved = viewModel::storePhotoInDevice,
                onNavigateToGallery = {
                    navController.navigate(route = Screen.GalleryScreen.route)
                },
                context = context
            )
        }
        composable(route = Screen.GalleryScreen.route) {
            val viewModel: CameraViewModel = viewModel(factory = CameraViewModel.Factory)
            val uiState by viewModel.uiState.collectAsState()
            GalleryScreen(
                photos = uiState.photos,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}