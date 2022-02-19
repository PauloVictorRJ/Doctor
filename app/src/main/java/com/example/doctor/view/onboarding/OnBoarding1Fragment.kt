package com.example.doctor.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.doctor.R
import com.example.doctor.databinding.FragmentOnBoarding1Binding
import com.example.doctor.model.SharedPref
import com.example.doctor.viewmodel.OnBoardingViewModel


class OnBoarding1Fragment : Fragment(R.layout.fragment_on_boarding1) {
    private var _binding: FragmentOnBoarding1Binding? = null
    private val binding: FragmentOnBoarding1Binding get() = _binding!!

    private var viewModel = OnBoardingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding1Binding.inflate(inflater, container, false)

//        SharedPref(requireParentFragment()).saveBoolean("onboarding", true)

        viewModel.checkOnboarding(requireParentFragment())

        var btnNext = binding.next
        var btnSkip = binding.skip

        btnNext.setOnClickListener {
            viewModel.btnFrag1Next(requireParentFragment())
        }

        btnSkip.setOnClickListener {
            viewModel.btnFrag1Skip(requireParentFragment())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
