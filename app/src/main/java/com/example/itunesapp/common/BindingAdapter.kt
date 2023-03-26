package com.example.itunesapp.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageUrl(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }
}