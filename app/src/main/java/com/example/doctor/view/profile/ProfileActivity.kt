package com.example.doctor.view.profile

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.doctor.R
import com.example.doctor.util.loadCircle
import com.example.doctor.view.finddoctors.FindDoctorsActivity
import com.example.doctor.viewmodel.ProfileViewModel
import com.google.android.material.textfield.TextInputEditText


class ProfileActivity : AppCompatActivity(R.layout.activity_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    private val loading: FrameLayout
        get() = findViewById(R.id.loading)

    private val profileName: TextInputEditText
        get() = findViewById(R.id.profile_name)

    private val profileContactNumber: TextInputEditText
        get() = findViewById(R.id.profile_contact_number)

    private val profileBirthDate: TextInputEditText
        get() = findViewById(R.id.profile_birth_date)

    private val profileLocation: TextInputEditText
        get() = findViewById(R.id.profile_location)

    private val profilePicture: ImageView
        get() = findViewById(R.id.profile_picture)

    private val btnContinue: Button
        get() = findViewById(R.id.btn_continue)

    private val btnRefresh: Button
        get() = findViewById(R.id.btn_refresh)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadUser()

        btnContinue.setOnClickListener {
            val intent = Intent(this, FindDoctorsActivity::class.java)
            startActivity(intent)
        }

        observeData()

        btnRefresh.setOnClickListener {
            viewModel.loadUser()
        }
    }

    private fun observeData() {
        viewModel.loading.observe(this) {
            loading.isVisible = it
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Deu erro!", Toast.LENGTH_LONG).show()
        }
        viewModel.profile.observe(this) {
            profileName.setText(it.name.first)
            profileContactNumber.setText(it.phone)
            profileBirthDate.setText(it.dob.date)
            profileLocation.setText(it.location.state)
            profilePicture.loadCircle(it.picture.large)
        }
    }
}