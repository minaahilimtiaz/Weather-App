package com.example.android.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.R
import com.example.android.weatherapp.models.CurrentWeatherModel
import kotlinx.android.synthetic.main.current_weather_layout.view.*

class HomeScreenAdapter:RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {

    var data = listOf<CurrentWeatherModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScreenAdapter.ViewHolder {
        return ViewHolder.inflateAndGetViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HomeScreenAdapter.ViewHolder, position: Int) {
        val item = data.get(position)
        holder.bindingViewToData(item)
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindingViewToData(item: CurrentWeatherModel) {
            itemView.current_weather_type_text.text = item.weatherType
            itemView.current_temp_text.text = item.temperature
            itemView.current_location_text.text = item.location
            itemView.current_date_text.text = item.date
        }

        companion object {
            fun inflateAndGetViewHolder(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.current_weather_layout,
                    parent, false)
                return ViewHolder(view)
            }

        }

    }
}
