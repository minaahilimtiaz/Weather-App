package com.example.android.weatherapp

import com.example.android.weatherapp.models.CurrentWeatherModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://samples.openweathermap.org/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()


interface WeatherApiService {

   @GET("data/2.5/weather")
   suspend fun getCurrentWeather(@Query("q") cityName: String , @Query("appid") appId: String ): Response<CurrentWeatherModel>
}

object WeatherApi {
    val retrofitService : WeatherApiService by lazy { retrofit.create(WeatherApiService::class.java) }
}
