package com.example.android.weatherapp.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.weatherapp.adapter.viewholder.CurrentWeatherViewHolder
import com.example.android.weatherapp.adapter.viewholder.ThreeHourlyWeatherViewHolder
import com.example.android.weatherapp.adapter.viewholder.WeeklyWeatherViewHolder
import com.example.android.weatherapp.models.*
import com.example.android.weatherapp.utilities.START_INDEX

private const val ITEM_VIEW_TYPE_CURRENT_WEATHER = 0
private const val ITEM_VIEW_TYPE_THREE_HOURLY_WEATHER = 1
private const val ITEM_VIEW_TYPE_WEEKLY_WEATHER= 2
private const val NUMBER_OF_ROWS = 3

class HomeScreenAdapter(activityContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val context = activityContext
    private var data: ForecastData = ForecastData()

    fun updateData(result: ForecastData) {
        clearPreviousData()
        assignNewData(result)
        notifyDataSetChanged()
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
                  holder.bindingViewToData(data.current[0], context)
            }
           is ThreeHourlyWeatherViewHolder -> {
                 holder.bindingViewToData(data.threeHourly, context)
           }
           is WeeklyWeatherViewHolder -> {
                  holder.bindingViewToData(data.weekly, context)
           }
        }
    }

    private fun clearPreviousData() {
        data.current.clear()
        data.weekly.clear()
        data.threeHourly.clear()
    }

    private fun assignNewData(result: ForecastData) {
        data.current.addAll(START_INDEX, result.current)
        data.threeHourly.addAll(START_INDEX, result.threeHourly)
        data.weekly.addAll(START_INDEX, result.weekly)
    }

}


