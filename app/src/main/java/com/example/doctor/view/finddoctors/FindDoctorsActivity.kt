package com.example.doctor.view.finddoctors

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.model.Doctor
import com.example.doctor.model.memory.SharedPref
import com.example.doctor.viewmodel.FindDoctorViewModel


class FindDoctorsActivity : AppCompatActivity(R.layout.activity_find_doctors) {
    private val findDoctorRv: RecyclerView
        get() = findViewById(R.id.find_doctor_rv)

    private val findDoctorsViewModel: FindDoctorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = this.getSharedPreferences("tokenFile",Context.MODE_PRIVATE)
        val tokenSaved = sharedPref.getString("token", null)

        if (tokenSaved != null) {
            findDoctorsViewModel.getDoctorList(tokenSaved)
        }

        findDoctorsViewModel.listDoctors.observe(this) {
            findDoctorRv.adapter = FindDoctorsRvAdapter(it)
        }
    }
}