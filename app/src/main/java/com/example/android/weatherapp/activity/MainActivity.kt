package com.example.android.weatherapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.weatherapp.R

class MainActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}