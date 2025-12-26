package com.rgcastrof.trustcam.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlipCameraAndroid
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CameraControls(
    onOpenGallery: () -> Unit,
    onTakePhoto: () -> Unit,
    onSwitchCamera: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onOpenGallery,
            modifier = Modifier.padding(42.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Photo,
                contentDescription = "Open gallery",
                modifier = Modifier.size(50.dp),
            )
        }

        IconButton(
            onClick = onTakePhoto,
            modifier = Modifier.padding(42.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PhotoCamera,
                contentDescription = "Take photo",
                modifier = Modifier.size(50.dp)
            )
        }

        IconButton(
            onClick = onSwitchCamera,
            modifier = Modifier.padding(42.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FlipCameraAndroid,
                contentDescription = "Camera switch",
                modifier = Modifier.size(50.dp)
            )
        }

    }
}