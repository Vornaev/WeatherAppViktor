package com.test.viktor.fake

import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.datasource.repository.interfaces.IWeatherMapRemoteDataSource
import com.test.viktor.model.response.daily.WeatherForecastDailyResponse
import com.test.viktor.model.response.pollution.AirPollutionResponse
import com.test.viktor.model.response.pollution.Coord
import com.test.viktor.model.response.realtime.WeatherRealTimeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepo : IWeatherMapRemoteDataSource{

    override fun getRealtimeWeatherData(
        name: String,
        format: String
    ): Flow<ApiResult<WeatherRealTimeResponse>> {
        return flow {
            emit(ApiResult.Success(null))
        }
    }

    override fun getAirPollution(coord: Coord): Flow<ApiResult<AirPollutionResponse>> {
        return flow {

            emit(ApiResult.Success(null))
        }
    }

    override fun getWeatherDailyForecast(
        coord: Coord,
        units: String
    ): Flow<ApiResult<WeatherForecastDailyResponse>> {
        return flow { emit(ApiResult.Success(null)) }
    }
}