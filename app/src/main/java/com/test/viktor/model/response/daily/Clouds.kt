package com.test.viktor.model.response.daily


import com.google.gson.annotations.SerializedName

data class Clouds(
      @SerializedName("all")
      var all: Int
)