package com.example.android.weatherapp.models

data class CurrentWeatherModel(
    val weather: List<WeatherModel>,
    val main: MainModel,
    val name: String
)

data class WeatherModel (
    val main: String
)

data class MainModel(
    val temp: Double
)
