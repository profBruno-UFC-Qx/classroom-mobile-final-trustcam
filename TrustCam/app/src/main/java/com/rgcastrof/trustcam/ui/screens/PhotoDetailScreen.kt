package com.rgcastrof.trustcam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.rgcastrof.trustcam.data.model.Photo
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun PhotoDetailScreen(
    photo: Photo,
    onBackClick: () -> Unit,
    onDeleteClick: (photo: Photo) -> Unit,
) {
    Box(
        modifier = Modifier.safeDrawingPadding()
    ) {
        AsyncImage(
            model = photo.filePath,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).fillMaxSize(),
            contentScale = ContentScale.Fit
        )
        Row(modifier = Modifier.align(Alignment.TopStart)) {
            val dateFormat = DateTimeFormatter
                    .ofPattern("MMM dd, yyyy\nHH:mm:ss")
                    .withZone(ZoneId.systemDefault())
                    .format(Instant.ofEpochMilli(currentPage.timestamp))
            IconButton(
                onClick = onBackClick,
            ) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
            }
            Text(
                text = "date",
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = null
            )
        }
        Row(modifier = Modifier.align(Alignment.BottomStart)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable(onClick = {
                            onDeleteClick(photo)
                            onBackClick()
                        })
                        .padding(10.dp)
                ) {
                    Icon(Icons.Default.DeleteOutline, contentDescription = null)
                    Text(
                        text = "Delete",
                        fontSize = 12.sp,
                    )
                }
        }
    }
}