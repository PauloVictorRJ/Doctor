package com.example.doctor.view.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.doctor.R
import com.example.doctor.databinding.FragmentOnBoarding2Binding
import com.example.doctor.viewmodel.OnBoardingViewModel


class OnBoarding2Fragment : Fragment(R.layout.fragment_on_boarding2) {
    private var _binding: FragmentOnBoarding2Binding? = null
    private val binding: FragmentOnBoarding2Binding get() = _binding!!

    private var viewModel = OnBoardingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding2Binding.inflate(inflater, container, false)

        var btnNext = binding.next
        var btnSkip = binding.skip

        btnNext.setOnClickListener{
            viewModel.btnFrag2Next(requireParentFragment())
        }

        btnSkip.setOnClickListener{
            viewModel.btnFrag2Skip(requireParentFragment())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}