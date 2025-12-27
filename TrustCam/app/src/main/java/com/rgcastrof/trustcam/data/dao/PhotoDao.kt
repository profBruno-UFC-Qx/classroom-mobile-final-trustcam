package com.rgcastrof.trustcam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rgcastrof.trustcam.data.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Insert
    suspend fun insertPhoto(photo: Photo)

    @Query("SELECT * FROM photos ORDER BY timestamp DESC")
    fun getAllPhotos(): Flow<List<Photo>>

    @Query("SELECT * FROM photos WHERE id = :photoId")
    suspend fun getPhotoById(photoId: Int?): Photo?
}