package com.example.doctor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(500)

        setTheme(R.style.Theme_Doctor)

        setContentView(R.layout.activity_main)
    }
}