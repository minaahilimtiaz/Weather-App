package com.example.android.weatherapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CurrentWeatherModel(
    val weather: List<WeatherModel>,
    val main: MainModel,
    val name: String
)

data class WeatherModel(
    val main: String,
    val icon: String
)

data class MainModel(
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

@Entity(tableName = "current_forecast_table")
data class CurrentWeatherModelDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val weatherInfo: String,
    val icon: String,
    val temp:Double,
    val temp_max: Double,
    val temp_min: Double,
    val name: String
)
