package com.example.android.weatherapp.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.adapter.WeeklyRecyclerAdapter
import com.example.android.weatherapp.models.WeeklyWeatherModel
import kotlinx.android.synthetic.main.weekly_weather_layout.view.*

class WeeklyWeatherViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindingViewToData(itemList: List<WeeklyWeatherModel>) {
        val adapter = WeeklyRecyclerAdapter()
        itemView.weekly_weather_recyclerview.adapter = adapter
        adapter.weeklyWeatherData = itemList
    }

    companion object {
        fun inflateAndGetViewHolder(parent: ViewGroup): WeeklyWeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(
                R.layout.weekly_weather_layout,
                parent, false)
            return WeeklyWeatherViewHolder(view)
        }
    }

}