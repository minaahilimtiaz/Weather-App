package com.example.android.weatherapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.adapter.viewholder.DailyWeatherViewHolder
import com.example.android.weatherapp.models.WeeklyWeatherModel

class WeeklyRecyclerAdapter : RecyclerView.Adapter<DailyWeatherViewHolder>() {

    var weeklyWeatherData = listOf<WeeklyWeatherModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return weeklyWeatherData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        return DailyWeatherViewHolder.inflateAndGetViewHolder(parent)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        holder.bindingViewToData(weeklyWeatherData[position])
    }
}
