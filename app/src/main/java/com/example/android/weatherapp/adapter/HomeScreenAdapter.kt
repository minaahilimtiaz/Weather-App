package com.example.android.weatherapp.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.adapter.viewholder.CurrentWeatherViewHolder
import com.example.android.weatherapp.adapter.viewholder.ThreeHourlyWeatherViewHolder
import com.example.android.weatherapp.adapter.viewholder.WeeklyWeatherViewHolder
import com.example.android.weatherapp.models.CurrentWeatherModel
import com.example.android.weatherapp.models.ThreeHourlyWeatherModel
import com.example.android.weatherapp.models.WeeklyWeatherModel

private const val ITEM_VIEW_TYPE_CURRENT_WEATHER = 0
private const val ITEM_VIEW_TYPE_THREE_HOURLY_WEATHER = 1
private const val ITEM_VIEW_TYPE_WEEKLY_WEATHER= 2
private const val NUMBER_OF_ROWS = 3

class HomeScreenAdapter(activityContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val context: Context = activityContext
    var currentWeatherData = listOf<CurrentWeatherModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var threeHourlyWeatherData = listOf<ThreeHourlyWeatherModel>()
        set(updatedValue) {
            field = updatedValue
        }

    var weeklyWeatherData = listOf<WeeklyWeatherModel>()
        set(newValue) {
            field = newValue
        }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return NUMBER_OF_ROWS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            return when (viewType) {
                ITEM_VIEW_TYPE_CURRENT_WEATHER -> CurrentWeatherViewHolder.inflateAndGetViewHolder(parent)
                ITEM_VIEW_TYPE_THREE_HOURLY_WEATHER -> ThreeHourlyWeatherViewHolder.inflateAndGetViewHolder(parent)
                ITEM_VIEW_TYPE_WEEKLY_WEATHER -> WeeklyWeatherViewHolder.inflateAndGetViewHolder(parent)
                else -> throw ClassCastException("Unknown viewType $viewType")
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrentWeatherViewHolder -> {
                holder.bindingViewToData(currentWeatherData[0], context)
            }
           is ThreeHourlyWeatherViewHolder -> {
                holder.bindingViewToData(threeHourlyWeatherData, context)
            }
           is WeeklyWeatherViewHolder -> {
                holder.bindingViewToData(weeklyWeatherData, context)
            }
        }
    }

}


