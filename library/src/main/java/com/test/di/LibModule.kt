package com.test.di

import com.test.library.DateOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LibModule {

    @Provides
    @Singleton
    fun provideDate() : DateOptions {
        return  DateOptions()
    }
}