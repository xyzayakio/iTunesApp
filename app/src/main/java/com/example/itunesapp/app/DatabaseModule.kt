package com.example.itunesapp.app

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.itunesapp.database.AlbumDao
import com.example.itunesapp.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return databaseBuilder(application, AppDatabase::class.java, "database").build()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(appDatabase: AppDatabase): AlbumDao {
        return appDatabase.getAlbumDao()
    }
}