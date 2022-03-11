package com.example.doctor.view.finddoctors.rvadapters

import android.provider.Settings.Global.getString
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R
import com.example.doctor.model.Doctors
import com.example.doctor.util.load


class FindDoctorsRvViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.find_doctor_item_poster)
    private val nome: TextView = view.findViewById(R.id.find_doctor_item_name)
    private val especializacao: TextView = view.findViewById(R.id.find_doctor_item_specialization)
    private val rank: TextView = view.findViewById(R.id.find_doctor_item_classification_value)
    private val experience: TextView = view.findViewById(R.id.find_doctor_item_experience)
    private val patientStories: TextView = view.findViewById(R.id.find_doctor_item_patient_stories_value)
    private val visualizacoes: TextView = view.findViewById(R.id.find_doctor_item_views_value)

    fun bind(item: Doctors) {
        image.load(item.photo)
        nome.text = item.name
        especializacao.text = item.specialization
        rank.text = item.classification.toString()
        experience.text = item.experience.toString()
        patientStories.text = item.patientStories.toString()
        visualizacoes.text = item.views.toString()
    }
}