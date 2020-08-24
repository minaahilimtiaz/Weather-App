package com.example.android.weatherapp.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.models.WeeklyWeatherModel
import kotlinx.android.synthetic.main.daily_weather_layout.view.*

class DailyWeatherViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindingViewToData(item: WeeklyWeatherModel) {
        itemView.daily_name_text.text = item.day
        itemView.daily_temperature_text.text = item.temperature
    }

    companion object {
        fun inflateAndGetViewHolder(parent: ViewGroup): DailyWeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(
                R.layout.daily_weather_layout,
                parent, false)
            return DailyWeatherViewHolder(view)
        }
    }

}