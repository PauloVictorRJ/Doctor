package com.example.doctor.model

data class ProfileResponse(val results: List<ProfileItem>)

data class ProfileItem(
    val name: ProfileName,
    val phone: String,
    val dob: ProfileRegister,
    val location: ProfileLocation
)

data class ProfileName(val first: String, val last: String)

data class ProfileRegister(val date: String)

data class ProfileLocation(val city: String)