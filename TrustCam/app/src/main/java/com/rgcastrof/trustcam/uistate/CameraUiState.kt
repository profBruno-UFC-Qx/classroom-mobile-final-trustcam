package com.rgcastrof.trustcam.uistate

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture

data class CameraUiState(
    val cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    val flashMode: Int = ImageCapture.FLASH_MODE_OFF
)
