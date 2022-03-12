package com.example.doctor.model

import com.google.gson.annotations.SerializedName


data class DoctorsResponse(
    @SerializedName("limit_page")
    val limitPage: Int,

    @SerializedName("doctors")
    val results: List<Doctors>
)

data class Doctors(
    val id: String,
    val photo: String,
    val name: String,
    val specialization: String,
    val classification: Float,
    val experience: Int,
    val patientStories: Int,
    val views: Int
)
