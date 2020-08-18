package com.example.android.weatherapp.models

import android.service.controls.templates.TemperatureControlTemplate

data class CurrentWeatherModel (
    val weatherType:String,
    val temperature: String,
    val location: String,
    val date: String
)