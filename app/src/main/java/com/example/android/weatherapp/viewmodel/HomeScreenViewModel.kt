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

class HomeScreenViewModel : ViewModel() {
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
            var weatherObj = WeatherApi.retrofitService.getCurrentWeather()
            try {
                val weatherData =weatherObj
                _currentWeather.value = weatherData.await()
                println(currentWeather.value)
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}