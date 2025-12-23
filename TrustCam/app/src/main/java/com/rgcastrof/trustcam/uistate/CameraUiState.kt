package com.rgcastrof.trustcam.uistate

import androidx.camera.core.CameraSelector
import com.rgcastrof.trustcam.data.model.Photo

data class CameraUiState(
    val cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA,
    val photos: List<Photo> = emptyList()
)
