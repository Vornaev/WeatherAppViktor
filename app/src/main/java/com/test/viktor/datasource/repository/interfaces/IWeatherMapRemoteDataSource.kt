package com.test.viktor.datasource.repository.interfaces

import com.test.viktor.model.response.realtime.WeatherRealTimeResponse
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.model.response.daily.WeatherForecastDailyResponse
import com.test.viktor.model.response.pollution.AirPollutionResponse
import com.test.viktor.model.response.pollution.Coord
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IWeatherMapRemoteDataSource {

   fun getRealtimeWeatherData(name: String, format: String) : Flow<ApiResult<WeatherRealTimeResponse>>

   fun getAirPollution(coord: Coord) : Flow<ApiResult<AirPollutionResponse>>

   fun getWeatherDailyForecast(coord: Coord, units : String) : Flow<ApiResult<WeatherForecastDailyResponse>>
}