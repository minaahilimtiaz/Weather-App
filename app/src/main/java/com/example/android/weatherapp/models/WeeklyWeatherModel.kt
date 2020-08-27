package com.example.android.weatherapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weekly_forecast_table")
data class WeeklyWeatherModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val day: String,
    val temperature: String,
    val iconId: String
)