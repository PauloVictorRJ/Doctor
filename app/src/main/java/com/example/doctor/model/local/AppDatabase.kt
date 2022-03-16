package com.example.doctor.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.doctor.model.local.dao.DoctorDao
import com.example.doctor.model.local.entity.DoctorEntity


@Database(
    entities = [DoctorEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun doctorDao(): DoctorDao

}