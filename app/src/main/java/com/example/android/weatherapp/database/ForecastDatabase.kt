package com.example.android.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.weatherapp.models.CurrentWeatherModelDb
import com.example.android.weatherapp.models.ThreeHourlyWeatherModel
import com.example.android.weatherapp.models.WeeklyWeatherModel

@Database(entities = [CurrentWeatherModelDb::class, ThreeHourlyWeatherModel::class,
    WeeklyWeatherModel::class], version = 1, exportSchema = false)
abstract class ForecastDatabase : RoomDatabase() {

    abstract val forecastDao: ForecastDao

    companion object {
        @Volatile
        private var INSTANCE: ForecastDatabase? = null

        fun getInstance(context: Context): ForecastDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ForecastDatabase::class.java,
                        "forecast_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}