package com.test.viktor.datasource.repository.impl

import com.test.viktor.model.response.daily.WeatherDailyResponse
import com.test.viktor.datasource.network.OpenWeatherHttpClient
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.datasource.repository.interfaces.IWeatherMapRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception


class WeatherMapRepository(
    private val client: OpenWeatherHttpClient,
    private val apiKey: String
) : IWeatherMapRemoteDataSource {

    override suspend fun getWeatherTodayByCityName(
        cityName: String,
        format: String
    ): Response<WeatherDailyResponse> {
        return client.getWeatherInfoByCityName(
            cityName = cityName,
            appID = apiKey,
            unit = format
        )
    }

    override fun getWeatherData(
        name: String,
        format: String
    ): Flow<ApiResult<WeatherDailyResponse>> {
        return flow {

            emit(ApiResult.Loading)

            try {
                val response = client.getWeatherInfoByCityName(
                    cityName = name,
                    appID = apiKey,
                    unit = format
                )

                if (response.isSuccessful && response.body() != null) {
                    emit(ApiResult.Success(response.body()))
                } else {
                    emit(ApiResult.Error("request was not successful"))
                }
            }catch (ex:Exception){
                emit(ApiResult.Error(ex.localizedMessage ?: "exception"))
            }
        }
    }


}