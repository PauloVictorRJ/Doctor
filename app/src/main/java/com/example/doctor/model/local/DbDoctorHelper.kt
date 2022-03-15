package com.example.doctor.model.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.doctor.model.local.entity.DoctorEntry
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_API_ID
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_EXPERIENCE
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_PATIENT_STORIES
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_PHOTO
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_SPECIALIZATION
import com.example.doctor.model.local.entity.DoctorEntry.COLUMN_NAME_VIEWS
import com.example.doctor.model.local.entity.DoctorEntry.COlUMN_NAME_NAME


class DbDoctorHelper(context: Context) {
    private val db = DbHelper(context, sqlCreationEntries, sqlDeleteEntries)

    fun insert(values: ContentValues.() -> Unit): Long {
        return db.writableDatabase.insert(
            DoctorEntry.TABLE_NAME,
            null,
            ContentValues().apply(values)
        )
    }

    fun update(id: Long, values: ContentValues.() -> Unit) {
        db.writableDatabase.update(
            DoctorEntry.TABLE_NAME,
            ContentValues().apply(values),
            "WHERE ID = ?",
            arrayOf(id.toString())
        )
    }

    fun delete(id: Long) {
        db.writableDatabase.delete(
            DoctorEntry.TABLE_NAME,
            "${BaseColumns._ID} = ?",
            arrayOf(id.toString())
        )
    }

    fun listAll() {
        val projection = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_API_ID,
            COLUMN_NAME_PHOTO,
            COlUMN_NAME_NAME,
            COLUMN_NAME_SPECIALIZATION,
            COLUMN_NAME_EXPERIENCE,
            COLUMN_NAME_PATIENT_STORIES,
            COLUMN_NAME_VIEWS
        )
        val order = "${BaseColumns._ID} DESC"
        val cursor = db.readableDatabase.query(
            DoctorEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            order
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getString(getColumnIndexOrThrow(COLUMN_NAME_API_ID))
                val photo = getString(getColumnIndexOrThrow(COLUMN_NAME_PHOTO))
                val name = getString(getColumnIndexOrThrow(COlUMN_NAME_NAME))
                val specialization = getString(getColumnIndexOrThrow(COLUMN_NAME_SPECIALIZATION))
                val experience = getString(getColumnIndexOrThrow(COLUMN_NAME_EXPERIENCE))
                val patientStories = getString(getColumnIndexOrThrow(COLUMN_NAME_PATIENT_STORIES))
                val views = getString(getColumnIndexOrThrow(COLUMN_NAME_VIEWS))

                println("$id | $name | $photo ")
            }
        }
        cursor.close()
    }

    companion object {
        private const val sqlCreationEntries =
            "CREATE TABLE ${DoctorEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${DoctorEntry.COLUMN_NAME_API_ID} TEXT," +
                    "${DoctorEntry.COLUMN_NAME_PHOTO} TEXT," +
                    "${DoctorEntry.COlUMN_NAME_NAME} TEXT," +
                    "${DoctorEntry.COLUMN_NAME_SPECIALIZATION} TEXT," +
                    "${DoctorEntry.COLUMN_NAME_EXPERIENCE} TEXT," +
                    "${DoctorEntry.COLUMN_NAME_PATIENT_STORIES} TEXT," +
                    "${DoctorEntry.COLUMN_NAME_VIEWS} TEXT)"

        private const val sqlDeleteEntries = "DROP TABLE IF EXISTS ${DoctorEntry.TABLE_NAME}"
    }
}
