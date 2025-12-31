package com.rgcastrof.trustcam.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rgcastrof.trustcam.data.model.Photo

@Composable
fun PhotoGrid(
    photos: List<Photo>,
    onPhotoClick: (Int) -> Unit,
    modifier: Modifier
) {
    if (photos.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No photos found.")
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            modifier = modifier
        ) {
            items(photos) { photo ->
                AsyncImage(
                    model = photo.filePath,
                    contentDescription = "Captured photo",
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onPhotoClick(photo.id) },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}