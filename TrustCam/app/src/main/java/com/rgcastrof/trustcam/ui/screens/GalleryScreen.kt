package com.rgcastrof.trustcam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rgcastrof.trustcam.data.model.Photo
import com.rgcastrof.trustcam.ui.composables.PhotoGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    photos: List<Photo>,
    onPhotoClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.safeDrawingPadding()
    ) {
        Text(
            text = "Reels",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        PhotoGrid(
            photos,
            onPhotoClick = onPhotoClick,
            modifier.padding(16.dp),
        )
    }
}