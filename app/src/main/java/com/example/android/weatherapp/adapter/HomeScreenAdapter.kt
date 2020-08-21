package com.example.android.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.models.CurrentWeatherModel
import kotlinx.android.synthetic.main.current_weather_layout.view.*

class HomeScreenAdapter:RecyclerView.Adapter<HomeScreenAdapter.CurrentWeatherViewHolder>() {

    var data = listOf<CurrentWeatherModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenAdapter.CurrentWeatherViewHolder {
        return CurrentWeatherViewHolder.inflateAndGetViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HomeScreenAdapter.CurrentWeatherViewHolder, position: Int) {
        val item = data.get(position)
        holder.bindingViewToData(item)
    }

    class CurrentWeatherViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindingViewToData(item: CurrentWeatherModel) {
            itemView.current_weather_type_text.text = item.weatherType
            itemView.current_temp_text.text = item.temperature
            itemView.current_location_text.text = item.location
            itemView.current_date_text.text = item.date
        }

        companion object {
            fun inflateAndGetViewHolder(parent: ViewGroup): CurrentWeatherViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.current_weather_layout,
                    parent, false)
                return CurrentWeatherViewHolder(view)
            }

        }

    }
}
