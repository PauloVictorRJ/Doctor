package com.example.doctor.view.finddoctors

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.viewmodel.FindDoctorViewModel


class FindDoctorsActivity : AppCompatActivity(R.layout.activity_find_doctors) {
    private val adapter = FindDoctorsRvAdapter()

    private val findDoctorRv: RecyclerView
        get() = findViewById(R.id.find_doctor_rv)

    private val loader: ProgressBar
        get() = findViewById(R.id.loader)

    private val findDoctorsViewModel: FindDoctorViewModel by viewModels()

    private var doctorLimitPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findDoctorRv.adapter = adapter

        setScrollView()

        observeData()

        findDoctorsViewModel.getDoctorList(1)
    }

    private fun observeData() {
        findDoctorsViewModel.listDoctors.observe(this) {
            adapter.updateList(it.results)
        }

        findDoctorsViewModel.doctorLimitPage.observe(this) {
            doctorLimitPage = it
        }

        findDoctorsViewModel.loading.observe(this) {
            Thread.sleep(2000)
            loader.isVisible = it
        }
    }

    private fun setScrollView() {
        var page = 1

        findDoctorRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val target = recyclerView.layoutManager as LinearLayoutManager
                val totalCountItems = target.itemCount
                val lastItemVisible = target.findLastVisibleItemPosition()

                val lastItem = lastItemVisible + 5 >= totalCountItems

                if ((totalCountItems > 0 && lastItem) && (page < doctorLimitPage && loader.isVisible.not())) {
                    findDoctorsViewModel.getDoctorList(++page)
                }
            }
        })
    }
}