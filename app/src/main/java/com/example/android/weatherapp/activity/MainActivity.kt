package com.example.android.weatherapp.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentTransaction
import com.example.android.weatherapp.BuildConfig.APPLICATION_ID
import com.example.android.weatherapp.R
import com.example.android.weatherapp.fragment.HomeScreenFragment
import com.google.android.material.snackbar.Snackbar
import java.util.*


private const val REQUEST_LOCATION = 1

class MainActivity : BaseActivity() , LocationListener {
    private lateinit  var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onStart() {
        super.onStart()
        checkPermissionsAndGetLocation()
    }

    private fun checkPermissionsAndGetLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
            return
        } else {
            requestLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocation() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L,
            1.0f, this)
        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val geoCoder = Geocoder(this, Locale.getDefault())
        location?.let {
            val addresses: List<Address> =
                geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            val cityName: String = addresses[0].locality
            replaceHomeScreenFragment(cityName)
        }
    }

    private fun replaceHomeScreenFragment(city: String) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.your_placeholder, HomeScreenFragment()).commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (grantResults.isEmpty() ||
            grantResults[0] == PackageManager.PERMISSION_DENIED
        ) {
            val view = findViewById<ConstraintLayout>(R.id.activity_main)
            Snackbar.make(view, R.string.allow_location, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.settings) {
                    // Displays App settings screen.
                    startActivity(Intent().apply {
                        //Show screen of details about a particular application.
                        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        data = Uri.fromParts("package", APPLICATION_ID, null)
                        //this activity will start a new task
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }.show()
        } else {
            requestLocation()
        }
    }

    override fun onLocationChanged(p0: Location) {
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String) {
    }

    override fun onProviderDisabled(provider: String) {
    }
}