package com.example.doctor.model


data class DoctorsResponse(val results: List<Doctor>)

data class Doctor(
    val image: String,
    val nome: String,
    val especializacao: String,
    val rank: Float,
    val visualizacoes: Int
)
