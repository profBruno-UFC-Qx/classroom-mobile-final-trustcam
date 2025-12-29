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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CameraViewModel(
    private val cameraRepository: CameraRepository
) : ViewModel() {

    private val _cameraSelector = MutableStateFlow(CameraSelector.DEFAULT_BACK_CAMERA)
    private val _selectedPhoto = MutableStateFlow<Photo>(Photo(-1, "", 0))
    private val allPhotosFromDatabase = cameraRepository.getAllPhotos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val uiState = combine(
        _cameraSelector,
        allPhotosFromDatabase,
        _selectedPhoto,
    ) { selector, photos, selectedPhoto ->
        CameraUiState(
            cameraSelector = selector,
            photos = photos,
            selectedPhoto = selectedPhoto
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = CameraUiState()
    )

    fun switchCamera() {
        _cameraSelector.update { current ->
            if (current == CameraSelector.DEFAULT_BACK_CAMERA) {
                CameraSelector.DEFAULT_FRONT_CAMERA
            } else {
                CameraSelector.DEFAULT_BACK_CAMERA
            }
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

    fun deletePhoto(photo: Photo) {
        viewModelScope.launch {
            cameraRepository.delete(photo)
        }
    }

    fun getPhotoById(photoId: Int?) {
        viewModelScope.launch {
            val photo = cameraRepository.getPhotoById(photoId)
            _selectedPhoto.value = photo
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