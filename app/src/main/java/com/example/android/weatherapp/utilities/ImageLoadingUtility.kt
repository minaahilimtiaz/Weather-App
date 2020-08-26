package com.example.android.weatherapp.utilities

import android.content.Context
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.android.weatherapp.R

//TODO: Add placeholders
fun ImageView.loadImage(iconId: String, context: Context) {

    val imgUri = context.getString(R.string.url, iconId).toUri().buildUpon().scheme("https").build()
    Glide.with(this.context)
        .load(imgUri)
        .into(this)
}
