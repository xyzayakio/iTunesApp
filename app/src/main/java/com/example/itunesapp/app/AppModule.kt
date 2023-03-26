package com.example.itunesapp.app

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext application: Application): Application {
        return application
    }
}