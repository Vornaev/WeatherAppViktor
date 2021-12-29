package com.test.viktor.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.viktor.R
import com.test.viktor.databinding.ActivityMainBinding
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.model.date.DateOperations
import com.test.viktor.model.enums.UnitFormat
import com.test.viktor.model.response.daily.WeatherForecastDailyResponse
import com.test.viktor.model.response.realtime.WeatherRealTimeResponse
import com.test.viktor.model.response.pollution.AirPollutionResponse
import com.test.viktor.viewmodel.WeatherMapViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: WeatherMapViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        init()
        loadData()
        subscribeWeatherData()
        subscribeAirPollutionData()
        subscribeForecastDailyData()

    }


    private fun init() {
        binding.contentMain.rootMain.alpha = 0f
        binding.swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        // viewModel.getRealTimeWeatherData()
        viewModel.getAirPollution()
        viewModel.getDailyForecast()
    }


    private fun subscribeWeatherData() {
        lifecycleScope.launchWhenStarted {
            viewModel.weatherDataFlow.collectLatest {
                when (it) {
                    is ApiResult.Error -> {
                        stopRefreshing()
                        handleError(it.networkError)
                    }
                    is ApiResult.Loading -> {
                        if (isRefreshing())
                            binding.contentMain.rootMain.animateResizeAndHide()
                    }
                    is ApiResult.Success -> {

                        it.data?.let { res ->
                            presentInView(res)
                        }
                        if (isRefreshing()) {
                            delay(300)
                            stopRefreshing()
                            binding.contentMain.rootMain.animateResizeAndShow()
                        } else {
                            binding.contentMain.rootMain.animateFadeInSlow()
                        }

                        //
                    }
                }
            }
        }
    }


    private fun subscribeForecastDailyData() {
        lifecycleScope.launchWhenStarted {
            viewModel.dailyForecastStateFlow.collect {
                when (it) {
                    is ApiResult.Error -> {
                        stopRefreshing()
                        handleError(it.networkError)
                    }
                    is ApiResult.Loading -> {
                        if (isRefreshing())
                            binding.contentMain.rootMain.animateResizeAndHide()
                    }
                    is ApiResult.Success -> {

                        it.data?.let { res ->
                            presentInView(res)
                        }
                        if (isRefreshing()) {
                            delay(300)
                            stopRefreshing()
                            binding.contentMain.rootMain.animateResizeAndShow()
                        } else {
                            binding.contentMain.rootMain.animateFadeInSlow()
                        }

                        //
                    }
                }
            }
        }
    }

    private fun isRefreshing(): Boolean {
        return binding.swipeRefresh.isRefreshing
    }

    private fun subscribeAirPollutionData() {
        lifecycleScope.launchWhenStarted {
            viewModel.airPollutionDataFlow.collectLatest {
                when (it) {
                    is ApiResult.Error -> {
                        stopRefreshing()
                        handleError(it.networkError)
                    }
                    is ApiResult.Loading -> {

                    }
                    is ApiResult.Success -> {
                        it.data?.let { res ->
                            presentInView(res)
                        }
                    }
                }
            }
        }
    }


    private fun presentInView(data: AirPollutionResponse) {
        val index = data.list.first().main.aqi
    }

    private fun presentInView(data: WeatherRealTimeResponse) {
        data.weather.firstOrNull()?.let {
            binding.contentMain.weatherActivityWeatherInfo.text = it.main
            loadImage(R.drawable.clear_sky)

        }
        val temp = "${data.main.temp.roundToInt()} ${getUnitFormat()}"
        binding.contentMain.weatherActivityTemp.text = temp
        binding.contentMain.weatherActivityRealFeelValue.text = data.main.feelsLike.toText()
    }

    private fun presentInView(data: WeatherForecastDailyResponse) {
        //Current Values
        data.current.weather.firstOrNull()?.let {
            binding.contentMain.weatherActivityWeatherInfo.text = it.main
            loadImage(R.drawable.clear_sky)
        }
        val temp = "${data.current.temp.roundToInt()} ${getUnitFormat()}"
        binding.contentMain.weatherActivityTemp.text = temp
        binding.contentMain.weatherActivityRealFeelValue.text = data.current.feelsLike.toText()


        //Daily Values
        val todayData = DateOperations.getTodayDate(data.daily)

        todayData?.let {
            binding.contentMain.weatherSunriseValue.text = DateOperations.getTimeForEpoch(it.sunrise)
            binding.contentMain.weatherSunsetValue.text = DateOperations.getTimeForEpoch(it.sunset)
            binding.contentMain.weatherActivityMaxTempValue.text = it.temp.max.toText()
            binding.contentMain.weatherActivityMinTempValue.text = it.temp.min.toText()
        }
    }

    private fun getUnitFormat(): String {
        return when (viewModel.unitFormat) {
            UnitFormat.METRIC -> "C"
            UnitFormat.IMPERIAL -> "F"
        }
    }


    private fun loadImage(drawable: Int) {
        Glide.with(this)
            .load(drawable)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(5, 1)))
            //.transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.weatherBackgroundImg)
    }

    private fun stopRefreshing() {
        binding.swipeRefresh.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}