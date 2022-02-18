package com.example.doctor.util

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.load(string: String) {
    Glide.with(this.context).load(string).into(this)
}

fun ImageView.loadCircle(string: String) {
    Glide.with(this.context).load(string).circleCrop().into(this)
}