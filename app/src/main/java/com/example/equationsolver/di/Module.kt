package com.example.equationsolver.di

import android.app.Application
import android.content.Context
import androidx.work.WorkManager
import com.example.equationsolver.presentation.app.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideWorkManager(app: Application): WorkManager =
        WorkManager.getInstance(app.applicationContext)


}