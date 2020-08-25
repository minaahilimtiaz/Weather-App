package com.example.android.weatherapp.adapter.viewholder

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
        setHourlyValues(itemView.first_hourly_layout, item[0])
        setHourlyValues(itemView.second_hourly_layout, item[1])
        setHourlyValues(itemView.third_hourly_layout, item[2])
        setHourlyValues(itemView.fourth_hourly_layout, item[3])
        setHourlyValues(itemView.fifth_hourly_layout, item[4])

    }

    private fun setHourlyValues(itemLayout: View, item: ThreeHourlyWeatherModel) {
        itemLayout.hourly_time_text.text = item.time
        itemLayout.hourly_temperature_text.text = item.temperature
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