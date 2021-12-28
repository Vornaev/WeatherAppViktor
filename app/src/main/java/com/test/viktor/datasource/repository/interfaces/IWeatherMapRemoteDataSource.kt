package com.test.viktor.datasource.repository.interfaces

import com.test.viktor.model.response.daily.WeatherDailyResponse
import com.test.viktor.datasource.network.helpers.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IWeatherMapRemoteDataSource {

   suspend fun getWeatherTodayByCityName(cityName: String, format :String) : Response<WeatherDailyResponse>

   fun getWeatherData(name: String, format: String) : Flow<ApiResult<WeatherDailyResponse>>
}