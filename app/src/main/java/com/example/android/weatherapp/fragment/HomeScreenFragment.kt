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
import com.example.android.weatherapp.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding: FragmentHomeScreenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_screen, container, false)

       /* val adapter = HomeScreenAdapter()
        binding.homeScreenRcv.adapter = adapter*/


        val viewModel: HomeScreenViewModel by lazy {
            ViewModelProviders.of(this).get(HomeScreenViewModel::class.java)
        }


       viewModel.currentWeather.observe(viewLifecycleOwner, Observer { currentData ->
            currentData?.let {
                val fetchedData = currentData.name
                println(fetchedData)
            }
        })


      /*  var currentWeatherList = preparecCurrentWeatherList()
        var threeHourlyList = prepareThreeHourlyList()
        var weeklyList = prepareDailyWeatherList()
        assignDataToAdapter(adapter, currentWeatherList, threeHourlyList, weeklyList)*/
        return binding.root
    }

  /*  private fun assignDataToAdapter(
        adapter: HomeScreenAdapter,
        currentWeatherList: List<CurrentWeatherModel>,
        threeHourlyList: List<ThreeHourlyWeatherModel>, weeklyList: List<WeeklyWeatherModel>
    ) {
        adapter.apply {
            currentWeatherData = currentWeatherList
            threeHourlyWeatherData = threeHourlyList
            weeklyWeatherData = weeklyList
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

    private fun prepareDailyWeatherList(): List<WeeklyWeatherModel> {
        return listOf<WeeklyWeatherModel>(
            WeeklyWeatherModel(
                "FRI", "17 C / 14 C"),  WeeklyWeatherModel("SAT", "17 C / 14 C"),
            WeeklyWeatherModel("SUN", "17 C / 14 C")
        )
    }
*/
}