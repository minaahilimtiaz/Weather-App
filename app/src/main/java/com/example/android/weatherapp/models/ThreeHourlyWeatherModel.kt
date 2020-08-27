package com.example.android.weatherapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "three_hourly_forecast_table")
data class ThreeHourlyWeatherModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,
    val time: String,
    val temperature: String,
    val iconId: String
)

data class ForecastWeatherModel(
    val list: List<ForecastList>
)

data class ForecastList(
    val dt_txt: String,
    val main: MainModel,
    val weather: List<WeatherModel>
)

