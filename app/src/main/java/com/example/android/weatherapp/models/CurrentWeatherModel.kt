package com.example.android.weatherapp.models

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
