package com.test.viktor.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.test.viktor.R
import com.test.viktor.databinding.ActivityMainBinding
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.util.DialogUtills
import com.test.viktor.viewmodel.WeatherMapViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

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

    }

    private fun init(){
        binding.contentMain.rootMain.alpha = 0f
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getWeatherData()
        }

        Glide.with(this)
            .load(R.drawable.clear_sky)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(5, 1)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.weatherBackgroundImg)

    }

    private fun subscribeWeatherData() {
        lifecycleScope.launchWhenStarted {
            viewModel.weatherDataFlow.collectLatest {
                when (it) {
                    is ApiResult.Error -> {
                        stopRefreshing()
                        DialogUtills.createDialogConfirm(
                            activity = this@MainActivity,
                            title = getString(R.string.error),
                            message = it.networkError,
                            buttonTitle = getString(R.string.ok),
                            onClicked = null
                        )
                    }
                    is ApiResult.Loading -> {
                        if (binding.swipeRefresh.isRefreshing)
                            binding.contentMain.rootMain.animateResizeAndHide()
                        //animate views
                    }
                    is ApiResult.Success -> {
                        if (binding.swipeRefresh.isRefreshing) {
                            delay(300)
                            stopRefreshing()
                            //binding.contentMain.weatherActivityRealFeelValue.animateShow()
                        }
                        binding.contentMain.rootMain.animateResizeAndShow()
                        //
                    }
                }
            }
        }
    }

    private fun loadData() {
        viewModel.getWeatherData()
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