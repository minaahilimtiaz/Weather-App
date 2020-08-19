package com.example.android.weatherapp.models

data class CurrentWeatherModel(
    val weatherType:String,
    val temperature: String,
    val location: String,
    val date: String
)