package com.example.doctor.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.doctor.R
import com.example.doctor.databinding.FragmentOnBoarding3Binding
import com.example.doctor.viewmodel.OnBoardingViewModel


class OnBoarding3Fragment : Fragment(R.layout.fragment_on_boarding3) {
    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding: FragmentOnBoarding3Binding get() = _binding!!

    private var viewModel = OnBoardingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding3Binding.inflate(inflater, container, false)

        var btnGetStarted = binding.getStarted
        btnGetStarted.setOnClickListener {
            viewModel.btnGetStarted(requireParentFragment())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}