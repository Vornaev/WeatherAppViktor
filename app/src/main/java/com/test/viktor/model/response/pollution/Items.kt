package com.test.viktor.model.response.pollution


import com.google.gson.annotations.SerializedName

data class Items(
      @SerializedName("components")
      var components: Components,
      @SerializedName("dt")
      var dt: Int,
      @SerializedName("main")
      var main: Main
)