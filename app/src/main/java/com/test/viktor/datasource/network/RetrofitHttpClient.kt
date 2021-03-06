package com.test.viktor.datasource.network

import android.content.Context
import com.google.gson.Gson
import com.test.viktor.BuildConfig
import com.test.viktor.datasource.network.helpers.ConnectivityInterceptor
import com.test.viktor.model.response.daily.WeatherForecastDailyResponse
import com.test.viktor.model.response.realtime.WeatherRealTimeResponse
import com.test.viktor.model.response.pollution.AirPollutionResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Duration
import java.util.concurrent.TimeUnit

class OpenWeatherHttpService {

    companion object {
        fun create(context: Context, baseUrl: String): OpenWeatherHttpClient {

            val httpClient = OkHttpClient.Builder().addInterceptor(ConnectivityInterceptor(context))
            if (BuildConfig.DEBUG) {
                httpClient.addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                })
            }


            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()

            return retrofit.create(OpenWeatherHttpClient::class.java)
        }
    }

}

interface OpenWeatherHttpClient {

    @GET("data/2.5/weather")
    suspend fun getWeatherInfoByCityName(
        @Query("q") cityName: String,
        @Query("appid") appID: String,
        @Query("units") unit: String
    ): Response<WeatherRealTimeResponse>

    @GET("data/2.5/air_pollution")
    suspend fun getAirPollution(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appID: String
    ): Response<AirPollutionResponse>

    @GET("data/2.5/onecall")
    suspend fun getDailyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appID: String,
        @Query("units") unit: String
    ):Response<WeatherForecastDailyResponse>
}
