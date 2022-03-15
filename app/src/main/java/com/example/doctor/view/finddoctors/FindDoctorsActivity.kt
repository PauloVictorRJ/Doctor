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
import com.example.doctor.model.DoctorsResponse
import com.example.doctor.model.local.DbDoctorHelper
import com.example.doctor.model.local.entity.DoctorEntry
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_API_ID
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_CLASSIFICATION
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_EXPERIENCE
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_PATIENT_STORIES
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_PHOTO
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_SPECIALIZATION
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_VIEWS
import com.example.doctor.model.local.entity.DoctorEntry.COlUMN_NAME_NAME
import com.example.doctor.viewmodel.FindDoctorViewModel


class FindDoctorsActivity : AppCompatActivity(R.layout.activity_find_doctors) {
    private lateinit var dbDoctorHelper: DbDoctorHelper

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

    private var doctorLocal = listOf<Doctors>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbDoctorHelper = DbDoctorHelper(this)

        findDoctorRv.adapter = adapter

        setScrollView()

        observeData()

        btnSqlLoadApi.setOnClickListener {
            findDoctorsViewModel.getDoctorList(1)
        }

        btnSqlLoadSql.setOnClickListener {
            listAll()
        }

        btnSqlSaveSql.setOnClickListener {
            save(doctorLocal)
        }

        btnSqlDeleteSql.setOnClickListener {

        }
    }

    private fun save(doctorLocal: List<Doctors>) {
        val id = dbDoctorHelper.insert {
            doctorLocal.forEach {
                put(COLUMN_NAME_API_ID, it.id)
                put(COLUMN_NAME_PHOTO, it.photo)
                put(COlUMN_NAME_NAME, it.name)
                put(COLUMN_NAME_SPECIALIZATION, it.specialization)
                put(COLUMN_NAME_CLASSIFICATION, it.classification)
                put(COLUMN_NAME_EXPERIENCE, it.experience)
                put(COLUMN_NAME_PATIENT_STORIES, it.patientStories)
                put(COLUMN_NAME_VIEWS, it.views)
            }
        }
    }

    private fun deleteDoctor(id: Long) {
        dbDoctorHelper.delete(id)
    }

    private fun listAll() {
        dbDoctorHelper.listAll()
    }

    private fun observeData() {
        findDoctorsViewModel.listDoctors.observe(this) {
            doctorLocal = it.results
            adapter.updateList(it.results)
        }

        findDoctorsViewModel.doctorLimitPage.observe(this) {
            doctorLimitPage = it
        }

        findDoctorsViewModel.loading.observe(this) {
            Thread.sleep(1000)
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