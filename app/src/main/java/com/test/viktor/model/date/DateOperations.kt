package com.test.viktor.model.date

import com.test.viktor.model.response.daily.Daily
import java.text.SimpleDateFormat
import java.util.*

object DateOperations {

     fun dateFormat(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            .apply { timeZone = TimeZone.getDefault() }
    }

     fun timeFormat() : SimpleDateFormat{
        return SimpleDateFormat("HH:mm", Locale.getDefault())
            .apply { TimeZone.getDefault() }
    }

    fun getTodayDate(list: List<Daily>): Daily? {
        return list.firstOrNull { predicate(it) }
    }

    fun getTimeForEpoch(epochTime : Long): String {
        return timeFormat().format(Date(epochTime * 1000))
    }

    private fun predicate(daily: Daily): Boolean {

        val date2 = dateFormat().format(Date(daily.dt * 1000))
        val localDate = dateFormat().format(System.currentTimeMillis())

        return date2 == localDate
    }
}