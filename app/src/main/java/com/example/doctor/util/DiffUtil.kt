package com.example.doctor.util

import androidx.recyclerview.widget.DiffUtil
import com.example.doctor.model.Doctors

class DiffUtil {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Doctors>() {
            override fun areItemsTheSame(oldItem: Doctors, newItem: Doctors): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Doctors, newItem: Doctors): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}