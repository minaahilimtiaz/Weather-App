package com.example.android.weatherapp.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtilities {

     fun checkConnectionStatus(context: Context): Boolean {
         val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
         val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
         val network = activeNetwork ?: return false
         return (network.isConnectedOrConnecting)
     }
 }