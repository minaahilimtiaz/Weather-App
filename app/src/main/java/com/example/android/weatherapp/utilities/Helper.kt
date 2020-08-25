package com.example.android.weatherapp.utilities

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class Helper {

    @SuppressLint("SimpleDateFormat")
    fun getDayFromDate(inputDate: String): String {
        val format = SimpleDateFormat(RESULT_DATE_FORMAT, Locale.ENGLISH)
        val date = format.parse(inputDate)
        val todayDate = SimpleDateFormat(DAY_FORMAT).format(date)
        return todayDate.toString()
    }

    fun getTimeFromDate(inputDate: String): String {
        val format: DateFormat = SimpleDateFormat(RESULT_DATE_FORMAT, Locale.ENGLISH)
        val date = format.parse(inputDate)
        val time = SimpleDateFormat(TIME_FORMAT).format(date)
        return time.toString()
    }

    fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT)
        return simpleDateFormat.format(Date())
    }

    fun convertToCelsius(kelvin: Double): Double {
        val converted = ((kelvin - KELVIN_CONVERSION))
        return round(converted)
    }

    fun formatWeeklyForecastTemperature(minTemp: Double, maxTemp: Double): String {
        val minCelsius = convertToCelsius(minTemp)
        val minCelsiusFormatted = round(minCelsius).toString()
        val maxCelsius = convertToCelsius(maxTemp)
        val maxCelsiusFormatted = round(maxCelsius).toString()
        return ("$minCelsiusFormatted / $maxCelsiusFormatted")
    }
}

