package com.test.viktor.viewmodel

import app.cash.turbine.test
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.datasource.repository.impl.WeatherMapRepository
import com.test.viktor.datasource.repository.interfaces.IWeatherMapRemoteDataSource
import com.test.viktor.fake.FakeRepo
import com.test.viktor.model.response.daily.WeatherForecastDailyResponse
import com.test.viktor.model.response.pollution.Coord
import com.test.viktor.model.response.realtime.WeatherRealTimeResponse
import com.test.viktor.view.OpenWeatherApplication
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Test
import org.mockito.*

class WeatherMapViewModelTest : BaseViewModelTest(){

    @Mock
    val app = OpenWeatherApplication()

    @Spy
    var repo: IWeatherMapRemoteDataSource = FakeRepo()

   private var viewModel: WeatherMapViewModel = WeatherMapViewModel(app, repo)

    override fun setup() {
        super.setup()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetDailyForecast() = runBlocking {

        viewModel.getDailyForecast()
        viewModel.dailyForecastStateFlow.test(3000) {

            assertEquals(awaitItem(), ApiResult.Loading)

            assertEquals(awaitItem(), ApiResult.Success(null))
        }

    }
}