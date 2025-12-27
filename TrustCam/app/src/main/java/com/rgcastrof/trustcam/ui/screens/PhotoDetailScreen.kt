package com.rgcastrof.trustcam.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.rgcastrof.trustcam.data.model.Photo

@Composable
fun PhotoDetailScreen(
    photo: Photo?,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.safeDrawingPadding()
    ) {
        AsyncImage(
            model = photo?.filePath,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).fillMaxSize(),
            contentScale = ContentScale.Fit
        )
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
        }
    }
}