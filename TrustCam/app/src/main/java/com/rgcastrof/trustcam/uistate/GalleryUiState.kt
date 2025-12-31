package com.rgcastrof.trustcam.uistate

import com.rgcastrof.trustcam.data.model.Photo

data class GalleryUiState(
    val photos: List<Photo> = emptyList(),
)