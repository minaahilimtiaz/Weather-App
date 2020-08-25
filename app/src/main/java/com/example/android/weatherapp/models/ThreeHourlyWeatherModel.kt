package com.example.android.weatherapp.models

data class ThreeHourlyWeatherModel(
    val time: String,
    val temperature: String
)

data class ForecastWeatherModel(
    val list: List<ForecastList>
)

data class ForecastList(
    val dt_txt: String,
    val main: MainModel
)

