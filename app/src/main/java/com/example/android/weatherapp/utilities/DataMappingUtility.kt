package com.example.android.weatherapp.utilities

import com.example.android.weatherapp.models.*

class DataMappingUtility {
    private val formatHelper = FormatUtility()

    fun convertToCurrentWeatherModelDb(currentWeather: CurrentWeatherModel): CurrentWeatherModelDb {
        return CurrentWeatherModelDb(START_INDEX, currentWeather.weather[0].main,
            currentWeather.weather[0].icon, currentWeather.main.temp, currentWeather.main.temp_max,
            currentWeather.main.temp_min, currentWeather.name)
    }

    fun convertToCurrentWeatherModel(currentWeather: CurrentWeatherModelDb): CurrentWeatherModel {
        val weatherList = mutableListOf(WeatherModel(currentWeather.weatherInfo, currentWeather.icon))
        val main = MainModel(currentWeather.temp, currentWeather.temp_max, currentWeather.temp_min)
        return CurrentWeatherModel(weatherList, main, currentWeather.name)
    }

    fun convertToThreeHourlyModelList(dataList: List<ForecastList>): List<ThreeHourlyWeatherModel> {
        val resultantList = mutableListOf<ThreeHourlyWeatherModel>()
        var index = START_INDEX
        for (forecast in dataList) {
            resultantList.add(
                ThreeHourlyWeatherModel(
                    index, formatHelper.getTimeFromDate(forecast.dt_txt),
                    formatHelper.convertToCelsius(forecast.main.temp).toString(),
                    forecast.weather[0].icon
                )
            )
            index++
        }
        return resultantList
    }

    fun convertToWeeklyModelList(dataList: List<ForecastList>): List<WeeklyWeatherModel> {
        val resultantList = mutableListOf<WeeklyWeatherModel>()
        var index = START_INDEX
        for (i in START until SIZE step DIFFERENCE) {
            val forecast = dataList[i]
            resultantList.add(
                WeeklyWeatherModel(
                    index, formatHelper.getDayFromDate(forecast.dt_txt),
                    formatHelper.formatWeeklyForecastTemperature(
                        forecast.main.temp_min,
                        forecast.main.temp_max
                    ),
                    forecast.weather[0].icon
                )
            )
            index++
        }
        return resultantList
    }
}