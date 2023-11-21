package com.nikobaby.market.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

const val BASE_URL = " https://api.thaistock2d.com"

const val API_GET_LIVE = "live"

const val API_GET_HISTORY = "2d_result"


fun getTimes(): Pair<Long, Long> {
    val myanmarTimeZone = getMyanmarTimeZone()
    val currentTimeInMyanmar: Date = Calendar.getInstance(myanmarTimeZone).time
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = currentTimeInMyanmar.time
    calendar.set(Calendar.HOUR_OF_DAY, 12)
    calendar.set(Calendar.MINUTE, 1)
    calendar.set(Calendar.SECOND, 0)
    val firstTaskTime = calendar.timeInMillis

    // Set the time for the second task (4:30 pm)
    calendar.set(Calendar.HOUR_OF_DAY, 16)
    calendar.set(Calendar.MINUTE, 30)
    calendar.set(Calendar.SECOND, 0)
    val secondTaskTime = calendar.timeInMillis

    return Pair(firstTaskTime, secondTaskTime)
}

fun getTodayName(): String {
    val calendar = Calendar.getInstance()
    return SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
}

fun getMyanmarTimeZone(): TimeZone {
    // Use the time zone ID for Myanmar
    val timeZoneId = "Asia/Yangon" // Myanmar time zone

    // Get the time zone instance
    return TimeZone.getTimeZone(timeZoneId)
}