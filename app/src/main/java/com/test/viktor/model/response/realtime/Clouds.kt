package com.test.viktor.model.response.realtime


import com.google.gson.annotations.SerializedName

data class Clouds(
      @SerializedName("all")
      var all: Int
)