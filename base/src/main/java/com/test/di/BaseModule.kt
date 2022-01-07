package com.test.di

import com.test.base.MyClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BaseModule {

    @Provides
    fun getMyClass() : MyClass{
        return MyClass("zvezda")
    }
}