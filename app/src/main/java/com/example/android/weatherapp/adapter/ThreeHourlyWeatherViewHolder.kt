package com.example.android.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.models.ThreeHourlyWeatherModel
import kotlinx.android.synthetic.main.hourly_weather_layout.view.*
import kotlinx.android.synthetic.main.three_hourly_weather_layout.view.*


class ThreeHourlyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindingViewToData(item: List<ThreeHourlyWeatherModel>) {
        setFirstHourlyValues(item[0])
        setSecondHourlyValues(item[1])
        setThirdHourlyValues(item[2])
        setFourthHourlyValues(item[3])
        setFifthHourlyValues(item[4])
    }

    private fun setFirstHourlyValues(item: ThreeHourlyWeatherModel) {
        itemView.first_hourly_layout.hourly_time_text.text = item.time
        itemView.first_hourly_layout.hourly_temperature_text.text = item.temperature
    }

    private fun setSecondHourlyValues(item: ThreeHourlyWeatherModel) {
        itemView.second_hourly_layout.hourly_time_text.text = item.time
        itemView.second_hourly_layout.hourly_temperature_text.text = item.temperature
    }

    private fun setThirdHourlyValues(item: ThreeHourlyWeatherModel) {
        itemView.third_hourly_layout.hourly_time_text.text = item.time
        itemView.third_hourly_layout.hourly_temperature_text.text = item.temperature
    }

    private fun setFourthHourlyValues(item: ThreeHourlyWeatherModel) {
        itemView.fourth_hourly_layout.hourly_time_text.text = item.time
        itemView.fourth_hourly_layout.hourly_temperature_text.text = item.temperature
    }

    private fun setFifthHourlyValues(item: ThreeHourlyWeatherModel) {
        itemView.fifth_hourly_layout.hourly_time_text.text = item.time
        itemView.fifth_hourly_layout.hourly_temperature_text.text = item.temperature
    }

    companion object {
        fun inflateAndGetViewHolder(parent: ViewGroup): ThreeHourlyWeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.three_hourly_weather_layout,
                parent, false)
            return ThreeHourlyWeatherViewHolder(view)
        }
    }

}