package com.example.itunesapp.utils

import android.app.Application
import com.example.itunesapp.repository.ApiRepository
import com.example.itunesapp.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun inject(application: Application)
    fun inject(activity: MainActivity)
    fun inject(repository: ApiRepository)
}