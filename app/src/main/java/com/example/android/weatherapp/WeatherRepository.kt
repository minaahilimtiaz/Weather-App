package com.example.android.weatherapp

import com.example.android.weatherapp.models.*
import com.example.android.weatherapp.utilities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class WeatherRepository() {

    private val weatherForecast: ForecastData = ForecastData()
    private val formatHelper: Helper = Helper()

    suspend fun fetchData(): ForecastData {
        withContext(Dispatchers.IO) {
            getForecast()
            getCurrentWeather()
        }
        return weatherForecast
    }

    private suspend fun getForecast() {
        withContext(Dispatchers.IO) {
            try {
                val result = WeatherApi.retrofitService.getFiveDaysWeather("Lahore",
                    APP_ID)
                if(result.isSuccessful) {
                    prepareThreeHourlyForecastData(result.body())
                    prepareWeeklyForecastData(result.body())
                }
            } catch (e: HttpException) {
                println("Exception ${e.message}")
            } catch (e: Throwable) {
                println("Repo + ${e.message}")
            }
        }
    }

    private fun prepareThreeHourlyForecastData(result: ForecastWeatherModel?) {
        val data = result ?: return
        val tempList = data.list.subList(START_INDEX, END_INDEX)
        for (forecast in tempList) {
            weatherForecast.threeHourly.add(
                ThreeHourlyWeatherModel(
                    formatHelper.getTimeFromDate(forecast.dt_txt),
                    formatHelper.convertToCelsius(forecast.main.temp).toString()
                )
            )
        }
    }

    private fun prepareWeeklyForecastData(result: ForecastWeatherModel?) {
        val data = result ?: return
        for (i in START until SIZE step DIFFERENCE) {
            val forecast = data.list[i]
            weatherForecast.weekly.add(
                WeeklyWeatherModel(
                    formatHelper.getDayFromDate(forecast.dt_txt),
                    formatHelper.formatWeeklyForecastTemperature(
                        forecast.main.temp_min,
                        forecast.main.temp_max
                    )
                )
            )
        }
    }


    private suspend fun getCurrentWeather() {
        withContext(Dispatchers.IO) {
            val weatherObj = WeatherApi.retrofitService.getCurrentWeather("Lahore", APP_ID)
            try {
                if (weatherObj.isSuccessful) {
                    val result = weatherObj.body()
                    result?.let {
                        weatherForecast.current.add(CurrentWeatherModel(result.weather, result.main,
                                result.name)
                        )
                    }
                }
            } catch (e: HttpException) {
                println("Exception1  ${e.message}")
            } catch (e: Throwable) {
                println("Repo1 + ${e.message}")
            }
        }
    }

}
