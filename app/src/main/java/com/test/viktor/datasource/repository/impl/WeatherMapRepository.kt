package com.test.viktor.datasource.repository.impl

import com.test.viktor.model.response.realtime.WeatherRealTimeResponse
import com.test.viktor.datasource.network.OpenWeatherHttpClient
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.datasource.repository.interfaces.IWeatherMapRemoteDataSource
import com.test.viktor.model.response.daily.WeatherForecastDailyResponse
import com.test.viktor.model.response.pollution.AirPollutionResponse
import com.test.viktor.model.response.pollution.Coord
import kotlinx.coroutines.flow.*
import retrofit2.Response


class WeatherMapRepository(
    private val client: OpenWeatherHttpClient,
    private val apiKey: String
) : IWeatherMapRemoteDataSource {

    private val errorMessage = "request was not successful"


    override fun getRealtimeWeatherData(
        name: String,
        format: String
    ): Flow<ApiResult<WeatherRealTimeResponse>> {
        return flow {
            emit(ApiResult.Loading)

            val response = client.getWeatherInfoByCityName(cityName = name, appID = apiKey, unit = format)
            this.resolveResponse(response)

        }.catch { ex ->
            emit(ApiResult.Error(ex.localizedMessage ?: errorMessage))
        }
    }

    override fun getWeatherDailyForecast(
        coord: Coord,
        units: String
    ): Flow<ApiResult<WeatherForecastDailyResponse>> {

        return flow {
            emit(ApiResult.Loading)

            val response = client.getDailyForecast(appID = apiKey, lat = coord.lat, lon = coord.lon, unit = units)
            this.resolveResponse(response)

        }.catch { ex ->
            emit(ApiResult.Error(ex.localizedMessage ?: errorMessage))
        }
    }

    override fun getAirPollution(coord: Coord): Flow<ApiResult<AirPollutionResponse>> {
        return flow {
            emit(ApiResult.Loading)

            val response = client.getAirPollution(appID = apiKey, lat = coord.lat, lon = coord.lon)
            this.resolveResponse(response)
        }.catch { ex ->
            emit(ApiResult.Error(ex.localizedMessage ?: errorMessage))
        }

    }

    private suspend fun <T> FlowCollector<ApiResult<T>>.resolveResponse(response: Response<T>) {
        if (response.isSuccessful && response.body() != null) {
            emit(ApiResult.Success(response.body()))
        } else {
            emit(ApiResult.Error(errorMessage))
        }
    }
}


