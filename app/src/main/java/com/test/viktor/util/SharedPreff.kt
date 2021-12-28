package com.test.viktor.util

import android.content.Context
import android.content.SharedPreferences

object SharedPreff {

    private const val PREF_ROOT = "com.humanity.pref.root"
    private const val KEY_CITY_NAME = "key.city.name"

    private fun<T> SharedPreferences.putValue(key:String, value:T?){
        val editor = this.edit()
        when (value) {
            is String? -> {editor.putString(key, value)}
            is Int ->{ editor.putInt(key, value)}
            is Boolean ->  { editor.putBoolean(key, value)}
            is Float ->  { editor.putFloat(key, value) }
            is Long ->  { editor.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not implemented yet!")
        }
        editor.apply()
    }

    private fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_ROOT, Context.MODE_PRIVATE)
    }

    fun saveCityName(context: Context, value: String) {
         getSharedPreference(context).putValue(KEY_CITY_NAME, value)
    }

    fun getCityName(context: Context): String? {
        return getSharedPreference(context).getString(KEY_CITY_NAME, null)
    }
}