package com.test.viktor.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.viktor.R
import com.test.viktor.databinding.ActivityMainBinding
import com.test.viktor.datasource.network.helpers.ApiResult
import com.test.viktor.util.DialogUtills
import com.test.viktor.viewmodel.WeatherMapViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherMapViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // setSupportActionBar(binding.toolbar)
        Glide.with(this)
            .load("https://wallpapermemory.com/uploads/679/sky-background-hd-1400x1050-56690.jpg")
            .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 2)))
            .into(binding.weatherBackgroundImg)


        binding.contentMain.weatherActivityWeatherInfo

        viewModel.getWeatherData()



        lifecycleScope.launchWhenStarted {
            viewModel.weatherDataFlow.collect{
                when(it){
                    is ApiResult.Error ->   {
                        DialogUtills.createDialogConfirm(
                            activity = this@MainActivity,
                            title=getString(R.string.error),
                            message = it.networkError,
                            buttonTitle = getString(R.string.ok),
                            onClicked = null)
                    }
                    is ApiResult.Loading -> {


                        //animate views
                    }
                    is ApiResult.Success -> {


                        //
                    }
                }
            }
        }


    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}