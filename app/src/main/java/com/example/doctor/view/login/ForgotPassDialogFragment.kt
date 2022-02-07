package com.example.doctor.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.doctor.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ForgotPassDialogFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forgot_pass, container, false)
    }

    companion object {
        const val TAG = "ForgotPassDialog"
    }
}