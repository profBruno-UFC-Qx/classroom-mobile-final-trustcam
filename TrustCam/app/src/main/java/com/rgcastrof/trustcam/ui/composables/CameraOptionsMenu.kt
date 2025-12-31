package com.rgcastrof.trustcam.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CameraOptionsMenu(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .padding(12.dp)
            .background(shape = CircleShape, color = Color.Black.copy(alpha = 0.5f)).padding(8.dp)
    ) {
        AnimatedVisibility(
            visible = expanded
        ) {
            Row(modifier = modifier.padding(end = 8.dp)) {
                MenuItem(
                    modifier = modifier.padding(end = 8.dp),
                    icon = Icons.Default.GridOn,
                    contentDescription = null,
                    onClick = {}
                )
                Text(
                    text = "9:16",
                    modifier = modifier
                )
                MenuItem(
                    modifier = modifier,
                    icon = Icons.Default.FlashOn,
                    contentDescription = null,
                    onClick = {}
                )
            }
        }
        Icon(
            imageVector = if (expanded) Icons.Default.Close else Icons.Default.Dashboard,
            contentDescription = null,
            modifier = modifier
                .clickable { expanded = !expanded }
        )
    }
}

@Composable
private fun MenuItem(
    modifier: Modifier,
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier.clickable(onClick = onClick),
    )
}