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
import com.example.doctor.model.Doctors
import com.example.doctor.model.factory.DatabaseFactory
import com.example.doctor.model.local.AppDatabase
import com.example.doctor.viewmodel.FindDoctorViewModel


class FindDoctorsActivity : AppCompatActivity(R.layout.activity_find_doctors) {
    private lateinit var dbRoom: AppDatabase

    private val adapter = FindDoctorsRvAdapter()

    private val findDoctorRv: RecyclerView
        get() = findViewById(R.id.find_doctor_rv)

    private val loader: ProgressBar
        get() = findViewById(R.id.loader)

    private val btnSqlLoadApi: Button
        get() = findViewById(R.id.btn_sql_load_api)

    private val btnSqlLoadSql: Button
        get() = findViewById(R.id.btn_sql_load_sql)

    private val btnSqlSaveSql: Button
        get() = findViewById(R.id.btn_sql_save_sql)

    private val btnSqlDeleteSql: Button
        get() = findViewById(R.id.btn_sql_delete_sql)

    private val findDoctorsViewModel: FindDoctorViewModel by viewModels()

    private var doctorLimitPage = 0

    private var doctorLocal: List<Doctors> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbRoom = DatabaseFactory.getDatabase(this)

        findDoctorRv.adapter = adapter

        setScrollView()

        observeData()

        btnSqlLoadApi.setOnClickListener {
            findDoctorsViewModel.getDoctorList(1)
        }

        btnSqlLoadSql.setOnClickListener {

        }

        btnSqlSaveSql.setOnClickListener {

        }

        btnSqlDeleteSql.setOnClickListener {

        }
    }

    private fun observeData() {
        findDoctorsViewModel.listDoctors.observe(this) {
            adapter.updateList(it.results)
        }

        findDoctorsViewModel.doctorLimitPage.observe(this) {
            doctorLimitPage = it
        }

        findDoctorsViewModel.loading.observe(this) {
            Thread.sleep(500)
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

    override fun onStop() {
        DatabaseFactory.removeInstance()
        super.onStop()
    }
}