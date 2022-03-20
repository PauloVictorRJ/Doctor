package com.example.doctor.view.finddoctors

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.compose.material.Button
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.model.Doctors
import com.example.doctor.util.DiffUtil.Companion.DIFF_UTIL
import com.example.doctor.view.finddoctors.rvadapters.FindDoctorsRvViewHolder


class FindDoctorsRvAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val diffUtil = AsyncListDiffer(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FindDoctorsRvViewHolder(
            inflater.inflate(
                R.layout.fragment_find_doctors_rv_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FindDoctorsRvViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount() = diffUtil.currentList.size

    fun updateList(items: List<Doctors>) {
        diffUtil.submitList(diffUtil.currentList.plus(items))
    }
}