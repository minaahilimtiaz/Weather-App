package com.example.android.weatherapp.utilities

import android.content.Context
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.android.weatherapp.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

private const val DATE_FORMAT = "MMMM dd, yyyy"

object Helper {
    const val APP_ID = "4dfdfc7144138f43bfa36b5b3a4b097f"

    fun getDayFromDate(inputDate: String): String{
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        val date: Date = format.parse(inputDate)
        val curr_date = SimpleDateFormat("EE").format(date)
        return curr_date.toString()
    }

    fun getTimeFromDate(inputDate: String): String{
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        val date: Date = format.parse(inputDate)
        val time = SimpleDateFormat("hh:mm").format(date)
        return time.toString()
    }

    fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT)
        val date: String = simpleDateFormat.format(Date())
        return date
    }

    fun convertToCelsius(fahrenheit: Double): Double{
        val converted = ((fahrenheit.toDouble() - 273.15))
        return roundDecimalPlaces(converted)
    }

    fun roundDecimalPlaces(input: Double): Double{
       return  (round(input * 10)/10)
    }

    fun formatWeeklyForecastTemperature(minTemp: Double, maxTemp: Double): String {
        val minCelsius = convertToCelsius(minTemp)
        val minCelsiusFormatted = roundDecimalPlaces(minCelsius).toString()
        val maxCelsius = convertToCelsius(maxTemp)
        val maxCelsiusFormatted = roundDecimalPlaces(maxCelsius).toString()
        return ("$minCelsiusFormatted / $maxCelsiusFormatted")
    }

}

fun ImageView.loadImage(iconId: String , context: Context) {

    val imgUri = context.getString(R.string.url, iconId).toUri().buildUpon().scheme("http").build()
    Glide.with(this.context)
        .load(imgUri)
        .into(this)
}

