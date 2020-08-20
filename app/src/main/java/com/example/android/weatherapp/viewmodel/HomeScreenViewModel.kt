package com.example.android.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.weatherapp.WeatherApi
import com.example.android.weatherapp.models.CurrentWeatherModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val APP_ID= "4dfdfc7144138f43bfa36b5b3a4b097f"

class HomeScreenViewModel : BaseViewModel() {
    private val viewModelJob = Job ()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _currentWeather = MutableLiveData<CurrentWeatherModel>()
    val currentWeather: LiveData<CurrentWeatherModel>
        get() = _currentWeather

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        coroutineScope.launch {
            var weatherObj = WeatherApi.retrofitService.getCurrentWeather("London", APP_ID)
            try {
                 if(weatherObj.isSuccessful) {
                     _currentWeather.value = weatherObj.body()
                 }
            } catch (e: HttpException) {
               println("Exception ${e.message}")
            } catch (e: Throwable) {
                println("Ooops: Something else went wrong")
            }
        }
    }
}