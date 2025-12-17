package com.rgcastrof.trustcam.viewmodel

import androidx.camera.core.CameraSelector
import androidx.lifecycle.ViewModel
import com.rgcastrof.trustcam.uistate.CameraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CameraViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CameraUiState())
    val uiState = _uiState.asStateFlow()

    fun switchCamera() {
        _uiState.update {
            it.copy(
                cameraSelector = if (it.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                    CameraSelector.DEFAULT_FRONT_CAMERA
                } else {
                    CameraSelector.DEFAULT_BACK_CAMERA
                }
            )
        }
    }
}