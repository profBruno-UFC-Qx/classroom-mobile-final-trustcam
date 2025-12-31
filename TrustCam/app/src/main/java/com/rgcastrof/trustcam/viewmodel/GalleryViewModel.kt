package com.rgcastrof.trustcam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rgcastrof.trustcam.data.TrustCamApplication
import com.rgcastrof.trustcam.data.repository.CameraRepository
import com.rgcastrof.trustcam.uistate.GalleryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val cameraRepository: CameraRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GalleryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            cameraRepository.getAllPhotos().collectLatest { photosList ->
                _uiState.update { currentState ->
                    currentState.copy(
                        photos = photosList
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TrustCamApplication)
                val repository = application.repository
                GalleryViewModel(repository)
            }
        }
    }
}