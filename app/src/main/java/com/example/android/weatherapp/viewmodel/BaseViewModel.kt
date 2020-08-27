package com.example.android.weatherapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

//To allow class to be inheritable we mark it open
open class BaseViewModel(application: Application) : AndroidViewModel(application) {

}