package com.example.doctor.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.doctor.model.SharedPref
import com.example.doctor.view.onboarding.OnBoarding1FragmentDirections
import com.example.doctor.view.onboarding.OnBoarding2FragmentDirections
import com.example.doctor.view.onboarding.OnBoarding3FragmentDirections


class OnBoardingViewModel : ViewModel() {

    fun checkOnboarding(fragment: Fragment) {
        var onboardingValue = SharedPref(fragment).readBoolean("onboarding")
        if (onboardingValue == false) {
            findNavController(fragment).navigate(OnBoarding1FragmentDirections.actionOnBoarding1FragmentToLoginFragment())
        }
    }

    fun btnGetStarted(fragment: Fragment) {
        SharedPref(fragment).saveBoolean("onboarding", false)
        findNavController(fragment).navigate(OnBoarding3FragmentDirections.actionOnBoarding3FragmentToLoginFragment())
    }

    fun btnFrag1Next(fragment: Fragment) {
        findNavController(fragment).navigate(OnBoarding1FragmentDirections.actionOnBoarding1FragmentToOnBoarding2Fragment())
    }

    fun btnFrag2Next(fragment: Fragment) {
        findNavController(fragment).navigate(OnBoarding2FragmentDirections.actionOnBoarding2FragmentToOnBoarding3Fragment())
    }

    fun btnFrag1Skip(fragment: Fragment) {
        findNavController(fragment).navigate(OnBoarding1FragmentDirections.actionOnBoarding1FragmentToLoginFragment())
    }

    fun btnFrag2Skip(fragment: Fragment) {
        findNavController(fragment).navigate(OnBoarding2FragmentDirections.actionOnBoarding2FragmentToLoginFragment())
    }
}