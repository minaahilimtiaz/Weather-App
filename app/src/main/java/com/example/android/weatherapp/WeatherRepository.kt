package com.example.android.weatherapp

import android.app.Application
import com.example.android.weatherapp.database.ForecastDatabase
import com.example.android.weatherapp.models.*
import com.example.android.weatherapp.utilities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class WeatherRepository(private val application: Application) {

    private val weatherForecast: ForecastData = ForecastData()
    private val networkHelper: NetworkUtilities = NetworkUtilities()
    private val dataMapper: DataMappingUtility = DataMappingUtility()
    private val databaseInstance: ForecastDatabase = ForecastDatabase.getInstance(application)

    suspend fun fetchData(city: String): ForecastData {
        withContext(Dispatchers.IO) {
            clearPreviousData()
            if (networkHelper.checkConnectionStatus(application.applicationContext)) {
                fetchDataFromServer(city)
                cacheDataToLocalDb(weatherForecast)
            } else {
                fetchDataFromLocalDb()
            }
        }
        return weatherForecast
    }

    private suspend fun fetchDataFromServer(city: String) {
            getForecast(city)
            getCurrentWeather(city)
    }

    private fun fetchDataFromLocalDb() {
        fetchCurrentWeatherFromLocalDb()
        fetchThreeHourlyWeatherFromLocalDb()
        fetchWeeklyWeatherFromLocalDb()
    }

    private fun cacheDataToLocalDb(weatherForecast: ForecastData) {
            clearPreviousDataFromLocalDb()
            insertInLocalDatabase(weatherForecast)
    }

    private fun insertInLocalDatabase(weatherForecast: ForecastData) {
        try {
            val insertData = dataMapper.convertToCurrentWeatherModelDb(weatherForecast.current[0])
            databaseInstance.forecastDao.insertCurrent(insertData)
            databaseInstance.forecastDao.insertAllThreeHourly(weatherForecast.threeHourly)
            databaseInstance.forecastDao.insertWeekly(weatherForecast.weekly)
        } catch (e: Throwable) {
            throw(e)
        }
    }

    private fun fetchCurrentWeatherFromLocalDb() {
        try {
            val result = databaseInstance.forecastDao.getCurrentForecast()
            val data = result ?: return
            val insertData = dataMapper.convertToCurrentWeatherModel(data)
            weatherForecast.current.add(insertData)
        } catch (e: Throwable) {
            throw(e)
        }
    }

    private fun fetchThreeHourlyWeatherFromLocalDb() {
        try {
            val result = databaseInstance.forecastDao.getThreeHourlyForecast()
            val data = result ?: return
            weatherForecast.threeHourly.addAll(START_INDEX, data)
        } catch (e: Throwable) {
            throw(e)
        }
    }

    private fun fetchWeeklyWeatherFromLocalDb() {
        try {
            val result = databaseInstance.forecastDao.getWeekly()
            val data = result ?: return
            weatherForecast.weekly.addAll(START_INDEX, data)
        } catch (e: Throwable) {
            throw(e)
        }
    }


    private suspend fun getForecast(city: String) {

        withContext(Dispatchers.IO) {
            try {
                val result = WeatherApi.retrofitService.getFiveDaysWeather(city, APP_ID)
                val data = result.body() ?: return@withContext
                if (result.isSuccessful) {
                    prepareThreeHourlyForecastData(data)
                    prepareWeeklyForecastData(data)
                }
            } catch (e: HttpException) {
                throw (e)
            } catch (e: Throwable) {
                throw(e)
            }
        }
    }

    private fun prepareThreeHourlyForecastData(result: ForecastWeatherModel) {
        val dataList = result.list.subList(START_INDEX, END_INDEX)
        val threeHourlyDataList = dataMapper.convertToThreeHourlyModelList(dataList)
        weatherForecast.threeHourly.addAll(START_INDEX, threeHourlyDataList)
    }

    private fun prepareWeeklyForecastData(result: ForecastWeatherModel) {
        val weeklyDataList = dataMapper.convertToWeeklyModelList(result.list)
        weatherForecast.weekly.addAll(START_INDEX, weeklyDataList)
    }


    private suspend fun getCurrentWeather(city: String) {
        withContext(Dispatchers.IO) {
            try {
                val result = WeatherApi.retrofitService.getCurrentWeather(city, APP_ID)
                val data = result.body() ?: return@withContext
                if (result.isSuccessful) {
                    prepareCurrentWeatherData(data)
                }
            } catch (e: HttpException) {
                throw (e)
            } catch (e: Throwable) {
                throw(e)
            }
        }
    }

    private fun prepareCurrentWeatherData(data: CurrentWeatherModel) {
        weatherForecast.current.add(CurrentWeatherModel(data.weather, data.main, data.name))
    }

    private fun clearPreviousData() {
        weatherForecast.current.clear()
        weatherForecast.threeHourly.clear()
        weatherForecast.weekly.clear()
    }

    private fun clearPreviousDataFromLocalDb() {
        databaseInstance.forecastDao.clearCurrentForecast()
        databaseInstance.forecastDao.clearThreeHourlyForecast()
        databaseInstance.forecastDao.clearWeeklyForecast()
    }

}
