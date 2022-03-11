package com.example.doctor.util

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.load(any: Any) {
    Glide.with(this.context).load(any).into(this)
}

fun ImageView.loadCircle(any: Any) {
    Glide.with(this.context).load(any).circleCrop().into(this)
}