package com.example.android.weatherapp.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.weatherapp.R
import com.example.android.weatherapp.models.ThreeHourlyWeatherModel
import kotlinx.android.synthetic.main.hourly_weather_layout.view.*
import kotlinx.android.synthetic.main.three_hourly_weather_layout.view.*


class ThreeHourlyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindingViewToData(item: List<ThreeHourlyWeatherModel>, context: Context) {
        setFirstHourlyValues(item[0], context)
        setSecondHourlyValues(item[1], context)
        setThirdHourlyValues(item[2], context)
        setFourthHourlyValues(item[3], context)
        setFifthHourlyValues(item[4], context)
    }

    private fun setFirstHourlyValues(item: ThreeHourlyWeatherModel, context: Context) {
        itemView.first_hourly_layout.hourly_time_text.text = item.time
        itemView.first_hourly_layout.hourly_temperature_text.text = item.temperature
        loadImage(itemView.first_hourly_layout.hourly_weather_image, item.iconId, context)
    }

    private fun setSecondHourlyValues(item: ThreeHourlyWeatherModel, context: Context) {
        itemView.second_hourly_layout.hourly_time_text.text = item.time
        itemView.second_hourly_layout.hourly_temperature_text.text = item.temperature
        loadImage(itemView.second_hourly_layout.hourly_weather_image, item.iconId, context)
    }

    private fun setThirdHourlyValues(item: ThreeHourlyWeatherModel, context: Context) {
        itemView.third_hourly_layout.hourly_time_text.text = item.time
        itemView.third_hourly_layout.hourly_temperature_text.text = item.temperature
        loadImage(itemView.third_hourly_layout.hourly_weather_image, item.iconId, context)
    }

    private fun setFourthHourlyValues(item: ThreeHourlyWeatherModel, context: Context) {
        itemView.fourth_hourly_layout.hourly_time_text.text = item.time
        itemView.fourth_hourly_layout.hourly_temperature_text.text = item.temperature
        loadImage(itemView.fourth_hourly_layout.hourly_weather_image, item.iconId, context)
    }

    private fun setFifthHourlyValues(item: ThreeHourlyWeatherModel, context: Context) {
        itemView.fifth_hourly_layout.hourly_time_text.text = item.time
        itemView.fifth_hourly_layout.hourly_temperature_text.text = item.temperature
        loadImage(itemView.fifth_hourly_layout.hourly_weather_image, item.iconId, context)
    }

    private fun loadImage(imageView: ImageView, iconId: String, context: Context) {
        Glide.with(imageView.context)
            .load(context.getString(R.string.url, iconId))
            .into(imageView)
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