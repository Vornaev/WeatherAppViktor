package com.test.viktor.viewmodel

import androidx.lifecycle.*
import com.test.viktor.model.response.daily.WeatherDailyResponse
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.datasource.repository.interfaces.IWeatherMapRemoteDataSource
import com.test.viktor.view.OpenWeatherApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherMapViewModel @Inject constructor(
    private val app: OpenWeatherApplication,
    private val repository: IWeatherMapRemoteDataSource
) : AndroidViewModel(app) {

    val weatherDataFlow : MutableStateFlow<ApiResult<WeatherDailyResponse>> = MutableStateFlow(
        ApiResult.Loading)

    init {

    }

    fun getDataForSkopje() {
        viewModelScope.launch {
            val result = repository.getWeatherTodayByCityName("Skopje", "metric")

            if (result.isSuccessful) {
                val p = result.body()
            } else {
                val p = result.errorBody()
            }

        }
    }

    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getWeatherData("Skopje", "metric").collect {
                    weatherDataFlow.value = it
            }
        }
    }
}