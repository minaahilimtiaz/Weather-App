package com.example.android.weatherapp.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.models.ThreeHourlyWeatherModel
import com.example.android.weatherapp.utilities.loadImage
import kotlinx.android.synthetic.main.hourly_weather_layout.view.*
import kotlinx.android.synthetic.main.three_hourly_weather_layout.view.*


class ThreeHourlyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindingViewToData(item: List<ThreeHourlyWeatherModel>, context: Context) {
        setHourlyValues(itemView.first_hourly_layout, item[0], context)
        setHourlyValues(itemView.second_hourly_layout, item[1], context)
        setHourlyValues(itemView.third_hourly_layout, item[2], context)
        setHourlyValues(itemView.fourth_hourly_layout, item[3], context)
        setHourlyValues(itemView.fifth_hourly_layout, item[4], context)
    }

    private fun setHourlyValues(itemLayout: View, item: ThreeHourlyWeatherModel, context: Context) {
        itemLayout.hourly_time_text.text = item.time
        itemLayout.hourly_temperature_text.text = item.temperature
        itemLayout.hourly_weather_image.loadImage(item.iconId, context)
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