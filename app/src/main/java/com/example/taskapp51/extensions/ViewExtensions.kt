package com.example.taskapp51.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.loadImage(url: String){
    Glide.with(this).load(url).circleCrop().into(this as ImageView)
}