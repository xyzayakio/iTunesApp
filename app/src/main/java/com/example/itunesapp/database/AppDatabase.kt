package com.example.itunesapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.itunesapp.model.Album

@Database(entities = [Album::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao
}