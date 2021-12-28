package com.test.viktor.di

import android.content.Context
import com.test.viktor.datasource.network.OpenWeatherHttpClient
import com.test.viktor.datasource.network.OpenWeatherHttpService
import com.test.viktor.util.Consts
import com.test.viktor.view.OpenWeatherApplication
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
    fun provideApp(@ApplicationContext app: Context) : OpenWeatherApplication {
        return app as OpenWeatherApplication
    }

    @Singleton
    @Provides
     fun provideOpenWeatherHttpClient(app : OpenWeatherApplication) : OpenWeatherHttpClient {
        return OpenWeatherHttpService.create(app, Consts.BASE_URL)
    }
}