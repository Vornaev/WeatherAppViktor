package com.test.viktor.viewmodel

import androidx.lifecycle.*
import com.test.viktor.model.response.realtime.WeatherRealTimeResponse
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.datasource.repository.interfaces.IWeatherMapRemoteDataSource
import com.test.viktor.model.coords.CoordsSkopje
import com.test.viktor.model.enums.UnitFormat
import com.test.viktor.model.response.daily.WeatherForecastDailyResponse
import com.test.viktor.model.response.pollution.AirPollutionResponse
import com.test.viktor.model.response.pollution.Coord
import com.test.viktor.view.OpenWeatherApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherMapViewModel @Inject constructor(
    private val app: OpenWeatherApplication,
    private val repository: IWeatherMapRemoteDataSource
) : AndroidViewModel(app) {

    var unitFormat = UnitFormat.METRIC

    val weatherDataFlow: MutableStateFlow<ApiResult<WeatherRealTimeResponse>> =
        MutableStateFlow(ApiResult.Loading)

    val airPollutionDataFlow: MutableStateFlow<ApiResult<AirPollutionResponse>> =
        MutableStateFlow(ApiResult.Loading)

    val dailyForecastStateFlow : MutableStateFlow<ApiResult<WeatherForecastDailyResponse>> = MutableStateFlow(ApiResult.Loading)


    fun getRealTimeWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRealtimeWeatherData(name = "Skopje", format = unitFormat.unit).collect {
                weatherDataFlow.value = it
            }
        }
    }

    fun getAirPollution() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAirPollution(Coord(CoordsSkopje.LAT, CoordsSkopje.LON)).collect {
                airPollutionDataFlow.value = it
            }
        }
    }

    fun getDailyForecast() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getWeatherDailyForecast(coord = Coord(CoordsSkopje.LAT, CoordsSkopje.LON), units = unitFormat.unit).collect {
                dailyForecastStateFlow.value = it
            }
        }
    }
}