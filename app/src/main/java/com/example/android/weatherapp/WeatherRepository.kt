package com.example.android.weatherapp

import com.example.android.weatherapp.models.*
import com.example.android.weatherapp.utilities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class WeatherRepository {

    private val weatherForecast: ForecastData = ForecastData()
    private val helper: Helper = Helper()

    suspend fun fetchData(): ForecastData {
        withContext(Dispatchers.IO) {
            getForecast()
            getCurrentWeather()
        }
        return weatherForecast
    }

    //TODO: PASS LOCATION VALUE FROM VIEWMODEL
    private suspend fun getForecast() {
        withContext(Dispatchers.IO) {
            val result = WeatherApi.retrofitService.getFiveDaysWeather("Lahore", APP_ID)
            try {
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
        result?.let {
            val tempList = result.list.subList(START_INDEX,END_INDEX)
            for( forecast in tempList){
                weatherForecast.threeHourly.add(
                    ThreeHourlyWeatherModel(
                    helper.getTimeFromDate(forecast.dt_txt),
                    helper.convertToCelsius(forecast.main.temp).toString()
                    )
                )
            }
        }
    }

    private fun prepareWeeklyForecastData(result: ForecastWeatherModel?) {
        result?.let {
            for (i in START until SIZE step DIFFERENCE) {
                val forecast = result.list[i]
                weatherForecast.weekly.add(
                    WeeklyWeatherModel(
                    helper.getDayFromDate(forecast.dt_txt),
                    helper.formatWeeklyForecastTemperature(
                        forecast.main.temp_min,
                        forecast.main.temp_max)
                )
                )
            }
        }
    }

    //TODO: PASS LOCATION VALUE FROM VIEWMODEL
    private suspend fun getCurrentWeather() {
        withContext(Dispatchers.IO) {
            val weatherObj = WeatherApi.retrofitService.getCurrentWeather(
                "Lahore", APP_ID)
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
