package com.test.viktor.model.response.pollution


import com.google.gson.annotations.SerializedName

data class Main(
      @SerializedName("aqi")
      var aqi: Int
)