package com.example.android.weatherapp.models

data class ForecastData(
    val current: MutableList<CurrentWeatherModel> = mutableListOf<CurrentWeatherModel>(),
    val  threeHourly: MutableList<ThreeHourlyWeatherModel> =  mutableListOf<ThreeHourlyWeatherModel>(),
    val weekly: MutableList<WeeklyWeatherModel> =  mutableListOf<WeeklyWeatherModel>()
)