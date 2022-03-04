package com.example.doctor.view.finddoctors.rvadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.model.Doctor
import com.example.doctor.util.load


class FindDoctorsRvViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.find_doctor_item_poster)
    private val nome: TextView = view.findViewById(R.id.find_doctor_item_name)
    private val especializacao: TextView = view.findViewById(R.id.find_doctor_item_specialization)
    private val rank: TextView = view.findViewById(R.id.find_doctor_item_classification_value)
    private val visualizacoes: TextView = view.findViewById(R.id.find_doctor_item_views_value)

    fun bind(item: Doctor) {
        image.load(item.image)
        nome.text = item.nome
        especializacao.text = item.especializacao
        rank.text = item.rank.toString()
        visualizacoes.text = item.visualizacoes.toString()
    }
}
