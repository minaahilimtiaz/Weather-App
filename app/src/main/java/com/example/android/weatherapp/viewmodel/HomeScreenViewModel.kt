package com.example.android.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.weatherapp.WeatherRepository
import com.example.android.weatherapp.models.*
import com.example.android.weatherapp.utilities.NO_NETWORK
import com.example.android.weatherapp.utilities.NO_RECORD
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeScreenViewModel : BaseViewModel() {

    private val viewModelJob = Job ()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var forecastData: ForecastData = ForecastData()
    var errorString = String()

    private val _eventDataFetched = MutableLiveData<Boolean>()
    val eventDataFetched: LiveData<Boolean>
        get() = _eventDataFetched

    private val _eventLoading = MutableLiveData<Boolean>()
    val eventLoading: LiveData<Boolean>
        get() = _eventLoading

    private val _eventError = MutableLiveData<Boolean>()
    val eventError: LiveData<Boolean>
        get() = _eventError

    private val repository = WeatherRepository()

    init {
      fetchDataFromRepository()
    }

    private fun fetchDataFromRepository() {
        coroutineScope.launch {
            try {
                val result: ForecastData = repository.fetchData()
                if(result.current.isEmpty() || result.threeHourly.isEmpty() || result.weekly.isEmpty() ) {
                    onErrorOccurred(NO_RECORD)
                } else {
                    onSuccess(result)
                }

            } catch (e: Exception) {
                onErrorOccurred(NO_NETWORK)
            }
        }
    }

    private fun onSuccess(result: ForecastData) {
        forecastData = result
        _eventDataFetched.value = true
        _eventLoading.value = true
    }

    private fun onErrorOccurred( errorMessage: String) {
        errorString = errorMessage
        _eventError.value = true
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}