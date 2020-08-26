package com.example.android.weatherapp.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.models.WeeklyWeatherModel
import com.example.android.weatherapp.utilities.loadImage
import kotlinx.android.synthetic.main.daily_weather_layout.view.*

class DailyWeatherViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindingViewToData(item: WeeklyWeatherModel, context: Context) {
        itemView.daily_name_text.text = item.day
        itemView.daily_temperature_text.text = item.temperature
        itemView.daily_weather_image.loadImage(item.iconId, context)
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