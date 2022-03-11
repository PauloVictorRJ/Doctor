package com.example.doctor.view.finddoctors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.model.Doctors
import com.example.doctor.view.finddoctors.rvadapters.FindDoctorsRvViewHolder


class FindDoctorsRvAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val doctors: MutableList<Doctors> = mutableListOf()

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
            is FindDoctorsRvViewHolder -> holder.bind(doctors[position])
        }
    }

    override fun getItemCount() = doctors.size

    fun updateList(items: List<Doctors>) {
        doctors.addAll(items)
        notifyDataSetChanged()
    }
}
