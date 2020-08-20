package com.example.android.weatherapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.weatherapp.viewmodel.HomeScreenViewModel
import com.example.android.weatherapp.R
import com.example.android.weatherapp.adapter.HomeScreenAdapter
import com.example.android.weatherapp.databinding.FragmentHomeScreenBinding
import com.example.android.weatherapp.models.CurrentWeatherModel

class HomeScreenFragment : BaseFragment() {
    val viewModel: HomeScreenViewModel by lazy {
        ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home_screen, container, false)

       viewModel.currentWeather.observe(viewLifecycleOwner, Observer { currentData ->
            currentData?.let {
                val adapter = HomeScreenAdapter()
                binding.homeScreenRcv.adapter = adapter
                adapter.currentWeatherData = listOf(currentData)
            }
        })
        return binding.root
    }
}