package com.example.android.weatherapp.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.weatherapp.R
import com.example.android.weatherapp.adapter.HomeScreenAdapter
import com.example.android.weatherapp.databinding.FragmentHomeScreenBinding
import com.example.android.weatherapp.viewmodel.HomeScreenViewModel
import kotlinx.android.synthetic.main.fragment_home_screen.*

class HomeScreenFragment : BaseFragment() {

    val viewModel: HomeScreenViewModel by lazy {
        ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_screen, container, false)
        binding.searchButton.setOnClickListener {
            viewModel.fetchData(binding.cityText.text.toString())
        }
        val adapter = HomeScreenAdapter(context!!)
        setOnDataUpdatedObserver(binding, adapter)
        return binding.root
    }

    private fun setOnDataUpdatedObserver(binding: FragmentHomeScreenBinding, adapter: HomeScreenAdapter ) {
        viewModel.eventDataUpdated.observe(viewLifecycleOwner, Observer {
            binding.homeScreenReyclerview.adapter = adapter
            adapter.currentWeatherData = viewModel.currentWeather
            adapter.threeHourlyWeatherData = viewModel.threeHourlyWeatherForecast
            adapter.weeklyWeatherData = viewModel.weeklyWeatherForecast
        })
    }
}