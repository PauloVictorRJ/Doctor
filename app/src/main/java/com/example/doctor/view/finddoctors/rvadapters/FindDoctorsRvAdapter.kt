package com.example.doctor.view.finddoctors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.model.Doctor
import com.example.doctor.view.finddoctors.rvadapters.FindDoctorsRvViewHolder


class FindDoctorsRvAdapter(private val items: List<Doctor>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            is FindDoctorsRvViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount() = items.size
}
