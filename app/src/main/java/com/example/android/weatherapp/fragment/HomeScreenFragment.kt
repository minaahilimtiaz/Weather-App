package com.example.android.weatherapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.weatherapp.R
import com.example.android.weatherapp.adapter.HomeScreenAdapter
import com.example.android.weatherapp.databinding.FragmentHomeScreenBinding
import com.example.android.weatherapp.models.CurrentWeatherModel

class HomeScreenFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_screen, container, false)

        var currentWeatherList = listOf<CurrentWeatherModel>(
            CurrentWeatherModel("Very Sunny", "17C",
                "Abottabad, Pakistan", "Friday, August 18, 2020"))

        val adapter = HomeScreenAdapter()
        binding.homeScreenRecyclerview.adapter = adapter
        adapter.data = currentWeatherList

        return binding.root
    }
}