package com.example.android.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.weatherapp.WeatherRepository
import com.example.android.weatherapp.models.*
import com.example.android.weatherapp.utilities.NO_NETWORK
import com.example.android.weatherapp.utilities.NO_RECORD
import com.example.android.weatherapp.utilities.START_INDEX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeScreenViewModel(city: String) : BaseViewModel() {

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
      fetchDataFromRepository(city)
    }

    fun fetchDataFromRepository(city: String) {
            coroutineScope.launch {
                try {
                    val result: ForecastData = repository.fetchData(city)
                    if (result.current.isEmpty() || result.threeHourly.isEmpty() || result.weekly.isEmpty()) {
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
        clearPreviousData()
        assignNewData(result)
        _eventDataFetched.value = true
        _eventLoading.value = true
    }

    private fun clearPreviousData() {
        forecastData.current.clear()
        forecastData.threeHourly.clear()
        forecastData.weekly.clear()
    }

    private fun assignNewData(result: ForecastData) {
        forecastData.current.addAll(START_INDEX, result.current)
        forecastData.threeHourly.addAll(START_INDEX, result.threeHourly)
        forecastData.weekly.addAll(START_INDEX, result.weekly)
    }

    private fun onErrorOccurred( errorMessage: String) {
        errorString = errorMessage
        _eventError.value = true
    }

    fun onDataFetched() {
        _eventDataFetched.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}

class HomeViewModelFactory(private val city: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(city) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}