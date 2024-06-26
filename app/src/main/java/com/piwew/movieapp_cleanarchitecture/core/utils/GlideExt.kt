package com.piwew.movieapp_cleanarchitecture.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .skipMemoryCache(true)
        .into(this)
}