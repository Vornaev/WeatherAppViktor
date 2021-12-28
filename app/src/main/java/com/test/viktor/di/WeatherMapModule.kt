package com.test.viktor.di

import com.test.viktor.datasource.network.OpenWeatherHttpClient
import com.test.viktor.datasource.repository.impl.WeatherMapRepository
import com.test.viktor.datasource.repository.interfaces.IWeatherMapRemoteDataSource
import com.test.viktor.util.Consts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object WeatherMapModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(httpClient: OpenWeatherHttpClient) : IWeatherMapRemoteDataSource {
        return WeatherMapRepository(httpClient, Consts.API_KEY)
    }
}