package com.example.itunesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.itunesapp.model.Album

@Dao
interface AlbumDao {

    @Query("SELECT * FROM album")
    fun getAll(): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(album: Album)

    @Delete
    suspend fun delete(album: Album)
}