package com.rgcastrof.trustcam.uistate

import androidx.camera.core.CameraSelector

data class CameraUiState(
    val cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
)
