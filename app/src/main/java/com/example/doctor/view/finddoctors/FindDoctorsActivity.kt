package com.example.doctor.view.finddoctors

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.viewmodel.FindDoctorViewModel


class FindDoctorsActivity : AppCompatActivity(R.layout.activity_find_doctors) {
    private val adapter = FindDoctorsRvAdapter()

    private val findDoctorRv: RecyclerView
        get() = findViewById(R.id.find_doctor_rv)

    private val findDoctorsViewModel: FindDoctorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findDoctorRv.adapter = adapter
        setScrollView()

        findDoctorsViewModel.getDoctorList(1)

        findDoctorsViewModel.listDoctors.observe(this) {
            adapter.updateList(it.results)
        }
    }

    private fun setScrollView() {
        var actualPage = 2

        findDoctorRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val target = recyclerView.layoutManager as LinearLayoutManager
                val totalCountItems = target.itemCount
                val lastItemVisible = target.findLastVisibleItemPosition()

                val lastItem = lastItemVisible + 5 >= totalCountItems

                if ((totalCountItems > 0 && lastItem) && actualPage <= findDoctorsViewModel.pageDoctorsLimit) {
                    findDoctorsViewModel.getDoctorList(actualPage)
                    actualPage++
                }
            }
        })
    }
}