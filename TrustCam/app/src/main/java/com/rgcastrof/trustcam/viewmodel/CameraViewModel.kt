package com.rgcastrof.trustcam.viewmodel

import androidx.camera.core.CameraSelector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rgcastrof.trustcam.data.CameraApplication
import com.rgcastrof.trustcam.data.model.Photo
import com.rgcastrof.trustcam.data.repository.CameraRepository
import com.rgcastrof.trustcam.uistate.CameraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CameraViewModel(
    private val cameraRepository: CameraRepository
) : ViewModel() {

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

    fun storePhotoInDevice(photoUriString: String) {
        if (photoUriString.isBlank()) return
        viewModelScope.launch {
            val photo = Photo(
                filePath = photoUriString,
                timestamp = System.currentTimeMillis()
            )
            cameraRepository.insert(photo)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val cameraRepository = (this[APPLICATION_KEY] as CameraApplication).repository
                CameraViewModel(cameraRepository)
            }
        }
    }
}