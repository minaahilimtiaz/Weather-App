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
import com.example.android.weatherapp.models.ThreeHourlyWeatherModel

class HomeScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_screen, container, false)
        var currentWeatherList = preparecCurrentWeatherList()
        var threeHourlyList = prepareThreeHourlyList()
        val adapter = HomeScreenAdapter()
        binding.homeScreenRcv.adapter = adapter
        assignDataToAdapter(adapter, currentWeatherList, threeHourlyList)
        return binding.root
    }

    private fun assignDataToAdapter(
        adapter: HomeScreenAdapter,
        currentWeatherList: List<CurrentWeatherModel>,
        threeHourlyList: List<ThreeHourlyWeatherModel>
    ) {
        adapter.apply {
            currentWeatherData = currentWeatherList
            threeHourlyWeatherData = threeHourlyList
        }

    }

    private fun prepareThreeHourlyList(): List<ThreeHourlyWeatherModel> {
        return listOf<ThreeHourlyWeatherModel>(
            ThreeHourlyWeatherModel("08pm", "10°C"),
            ThreeHourlyWeatherModel("09pm", "30°C"),
            ThreeHourlyWeatherModel("12am", "36°C"),
            ThreeHourlyWeatherModel("03am", "02°C"),
            ThreeHourlyWeatherModel("06am", "05°C")
        )
    }

    private fun preparecCurrentWeatherList(): List<CurrentWeatherModel> {
        return listOf<CurrentWeatherModel>(
            CurrentWeatherModel(
                "Humid", "17C",
                "Abottabad, Pakistan", "Tuesday Augus 19, 2020"
            )
        )
    }

}