package com.example.android.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.weatherapp.WeatherApi
import com.example.android.weatherapp.models.CurrentWeatherModel
import com.example.android.weatherapp.models.ForecastWeatherModel
import com.example.android.weatherapp.models.ThreeHourlyWeatherModel
import com.example.android.weatherapp.models.WeeklyWeatherModel
import com.example.android.weatherapp.utilities.Helper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeScreenViewModel : BaseViewModel() {

    private val viewModelJob = Job ()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val currentWeather = mutableListOf<CurrentWeatherModel>()
    val threeHourlyWeatherForecast = mutableListOf<ThreeHourlyWeatherModel>()
    val weeklyWeatherForecast = mutableListOf<WeeklyWeatherModel>()

    private val _eventDataUpdated = MutableLiveData<Boolean>()
    val eventDataUpdated: LiveData<Boolean>
        get() = _eventDataUpdated

    init {
      fetchData()
    }

    private fun fetchData() {
        getCurrentWeather()
        getForecast()
    }

    private fun getForecast() {
        coroutineScope.launch {
            val forecastObj = WeatherApi.retrofitService.getFiveDaysWeather("Lahore",
                Helper.APP_ID)
            try {
                if(forecastObj.isSuccessful) {
                    prepareThreeHourlyForecastData(forecastObj.body())
                    prepareWeeklyForecastData(forecastObj.body())
                }
                onDataUpdated()
            } catch (e: HttpException) {
                println("Exception ${e.message}")
            } catch (e: Throwable) {
                println("Ooops: Something else went wrong")
            }
        }
    }

    private fun prepareThreeHourlyForecastData(forecastObj: ForecastWeatherModel?) {
        forecastObj?.let {
            val tempList = forecastObj.list.subList(0,5)
            for( obj in tempList){
                threeHourlyWeatherForecast.add(ThreeHourlyWeatherModel(
                        Helper.getTimeFromDate(obj.dt_txt),
                        Helper.convertToCelsius(obj.main.temp).toString()
                    )
                )
            }
        }
    }

    private fun prepareWeeklyForecastData(forecastObj: ForecastWeatherModel?) {
        forecastObj?.let {
            for (obj in 11 until 40 step 8) {
                val tempObj = forecastObj.list[obj]
                weeklyWeatherForecast.add(WeeklyWeatherModel(
                        Helper.getDayFromDate(tempObj.dt_txt),
                        Helper.formatWeeklyForecastTemperature(
                            tempObj.main.temp_min,
                            tempObj.main.temp_max)
                    )
                )
            }
        }
    }

    private fun getCurrentWeather() {
        coroutineScope.launch {
            val weatherObj = WeatherApi.retrofitService.getCurrentWeather("Lahore",
                Helper.APP_ID)
            try {
                 if(weatherObj.isSuccessful) {
                     val result = weatherObj.body()
                     result?.let{
                         currentWeather.add(CurrentWeatherModel(result.weather, result.main, result.name))
                     }
                 }
            } catch (e: HttpException) {
               println("Exception ${e.message}")
            } catch (e: Throwable) {
                println("Ooops: Something else went wrong")
            }
        }
    }

    fun onDataUpdated() {
        _eventDataUpdated.value = true
    }
}