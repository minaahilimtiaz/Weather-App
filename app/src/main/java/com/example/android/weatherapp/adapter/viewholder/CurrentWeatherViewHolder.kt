package com.example.android.weatherapp.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.models.CurrentWeatherModel
import com.example.android.weatherapp.utilities.FormatUtility
import com.example.android.weatherapp.utilities.loadImage
import kotlinx.android.synthetic.main.current_weather_layout.view.*

class CurrentWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindingViewToData(item: CurrentWeatherModel, context: Context) {
        val formatHelper = FormatUtility()
        itemView.current_weather_type_text.text = item.weather[0].main
        itemView.current_temp_text.text = formatHelper.convertToCelsius(item.main.temp).toString()
        itemView.current_location_text.text = item.name
        itemView.current_date_text.text = formatHelper.getCurrentDate()
        itemView.current_weather_image_view.loadImage(item.weather[0].icon, context)
    }

    companion object {
        fun inflateAndGetViewHolder(parent: ViewGroup): CurrentWeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(
                R.layout.current_weather_layout,
                parent, false
            )
            return CurrentWeatherViewHolder(view)
        }
    }

}
