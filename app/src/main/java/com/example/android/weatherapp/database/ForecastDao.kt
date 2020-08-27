package com.example.android.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.weatherapp.models.CurrentWeatherModelDb
import com.example.android.weatherapp.models.ThreeHourlyWeatherModel
import com.example.android.weatherapp.models.WeeklyWeatherModel

@Dao
interface ForecastDao {
    @Query("select * from current_forecast_table")
    fun getCurrentForecast(): CurrentWeatherModelDb?

    @Query("SELECT * FROM three_hourly_forecast_table ORDER BY id ASC LIMIT 5")
    fun getThreeHourlyForecast(): List<ThreeHourlyWeatherModel>?

    @Query("select * from weekly_forecast_table")
    fun getWeekly(): List<WeeklyWeatherModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrent(currentData: CurrentWeatherModelDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllThreeHourly(threeHourlyData: List<ThreeHourlyWeatherModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeekly(weeklyData: List<WeeklyWeatherModel>)

    @Query("DELETE FROM current_forecast_table")
    fun clearCurrentForecast()

    @Query("DELETE FROM three_hourly_forecast_table")
    fun clearThreeHourlyForecast()

    @Query("DELETE FROM weekly_forecast_table")
    fun clearWeeklyForecast()
}