package com.rgcastrof.trustcam.data

import android.app.Application
import com.rgcastrof.trustcam.data.repository.CameraRepository

class CameraApplication : Application() {
    val database by lazy { CameraDatabase.getInstance(this) }
    val repository by lazy { CameraRepository(database.photoDao()) }
}