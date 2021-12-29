package com.test.viktor.model.response.daily


import com.google.gson.annotations.SerializedName

data class Daily(
      @SerializedName("clouds")
      var clouds: Int,
      @SerializedName("dew_point")
      var dewPoint: Double,
      @SerializedName("dt")
      var dt: Long,
      @SerializedName("feels_like")
      var feelsLike: FeelsLike,
      @SerializedName("humidity")
      var humidity: Int,
      @SerializedName("moon_phase")
      var moonPhase: Double,
      @SerializedName("moonrise")
      var moonrise: Long,
      @SerializedName("moonset")
      var moonset: Long,
      @SerializedName("pop")
      var pop: Double,
      @SerializedName("pressure")
      var pressure: Int,
      @SerializedName("rain")
      var rain: Double,
      @SerializedName("sunrise")
      var sunrise: Long,
      @SerializedName("sunset")
      var sunset: Long,
      @SerializedName("temp")
      var temp: Temp,
      @SerializedName("uvi")
      var uvi: Double,
      @SerializedName("weather")
      var weather: List<Weather>,
      @SerializedName("wind_deg")
      var windDeg: Int,
      @SerializedName("wind_gust")
      var windGust: Double,
      @SerializedName("wind_speed")
      var windSpeed: Double
)