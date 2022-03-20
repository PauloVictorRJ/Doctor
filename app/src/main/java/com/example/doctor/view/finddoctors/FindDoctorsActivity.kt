package com.example.doctor.view.finddoctors

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.databinding.ActivityFindDoctorsBinding
import com.example.doctor.model.DataResult
import com.example.doctor.model.factory.DatabaseFactory
import com.example.doctor.viewmodel.FindDoctorViewModel


class FindDoctorsActivity : AppCompatActivity(R.layout.activity_find_doctors) {
    private val binding by lazy { ActivityFindDoctorsBinding.inflate(layoutInflater) }
    private val findDoctorsViewModel: FindDoctorViewModel by viewModels()
    private val findDoctorRv by lazy { binding.findDoctorRv }
    private val adapterDoctorRV by lazy { FindDoctorsRvAdapter() }
    private val loader by lazy { binding.loader }
    private var doctorLimitPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findDoctorRv.adapter = adapterDoctorRV
        setScrollView()

        findDoctorsViewModel.getDoctorList(1).observe(this) {
            when (it) {
                is DataResult.Success -> {
                    adapterDoctorRV.updateList(it.data.results)
                    doctorLimitPage = it.data.limitPage
                    loader.isVisible = false
                }
                is DataResult.Loading -> {
                    loader.isVisible = true
                }
                else -> {}
            }
            setContentView(binding.root)
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

                if ((totalCountItems > 0 && lastItem) && (page < doctorLimitPage && loader.isVisible)) {
                    ++page
                    findDoctorsViewModel.getDoctorList(page).observe(this@FindDoctorsActivity) {
                        when (it) {
                            is DataResult.Success -> {
                                adapterDoctorRV.updateList(it.data.results)
                            }
                            else -> {}
                        }
                    }
                }
            }
        })
    }

    override fun onStop() {
        DatabaseFactory.removeInstance()
        super.onStop()
    }
}