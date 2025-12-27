package com.rgcastrof.trustcam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rgcastrof.trustcam.data.dao.PhotoDao
import com.rgcastrof.trustcam.data.model.Photo

@Database(entities = [Photo::class], version = 1)
abstract class CameraDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    companion object {
        @Volatile
        private var INSTANCE: CameraDatabase? = null

        fun getInstance(context: Context): CameraDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CameraDatabase::class.java,
                    "trustcam_database"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}