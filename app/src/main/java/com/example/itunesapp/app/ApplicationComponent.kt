package com.example.itunesapp.app

import android.app.Application
import com.example.itunesapp.api.ApiRepository
import com.example.itunesapp.database.AlbumDao
import com.example.itunesapp.main.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(application: Application)
    fun inject(activity: MainActivity)
    fun inject(repository: ApiRepository)
    fun inject(albumDao: AlbumDao)
}