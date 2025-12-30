package com.rgcastrof.trustcam.uistate

import com.rgcastrof.trustcam.data.model.Photo

data class PhotoDetailUiState(
    val photos: List<Photo>? = null,
    val selectedPhotoId: Int? = null,
    val detailOverlay: Boolean = true
)