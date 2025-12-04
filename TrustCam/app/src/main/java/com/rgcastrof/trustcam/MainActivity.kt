package com.rgcastrof.trustcam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.rgcastrof.trustcam.ui.theme.TrustCamTheme
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private val cameraPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            TODO()
        } else {
            Toast.makeText(
                this@MainActivity,
                "This app needs permission to take photos",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) -> {
                TODO()
            } else -> {
                cameraPermissionRequest.launch(Manifest.permission.CAMERA)
            }
        }

        enableEdgeToEdge()
        setContent {
            TrustCamTheme {
            }
        }
    }
}