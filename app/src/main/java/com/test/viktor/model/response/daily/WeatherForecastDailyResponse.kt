package com.test.viktor.model.response.daily


import com.google.gson.annotations.SerializedName

data class WeatherForecastDailyResponse(
      @SerializedName("current")
      var current: Current,
      @SerializedName("daily")
      var daily: List<Daily>,
      @SerializedName("lat")
      var lat: Double,
      @SerializedName("lon")
      var lon: Double,
      @SerializedName("timezone")
      var timezone: String,
      @SerializedName("timezone_offset")
      var timezoneOffset: Int
)