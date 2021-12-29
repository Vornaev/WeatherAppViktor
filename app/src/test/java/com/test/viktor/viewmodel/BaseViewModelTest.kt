package com.test.viktor.viewmodel

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseViewModelTest {


    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    open fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    protected fun <T> anyNotNullObj(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
}