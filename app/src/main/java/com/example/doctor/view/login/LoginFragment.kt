package com.example.doctor.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.doctor.R
import com.example.doctor.databinding.FragmentLoginBinding
import com.example.doctor.model.DataResult
import com.example.doctor.viewmodel.LoginViewModel


class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()
    var dialog = ForgotPassDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val loginEmail = binding.loginEmail
        val loginPassword = binding.loginPassword
        val loading = binding.loading

        val btnForgot = binding.btnForgot
        btnForgot.setOnClickListener {
            dialog.show(parentFragmentManager, ForgotPassDialogFragment.TAG)
        }

        val btnLogin = binding.btnLogin
        btnLogin.setOnClickListener {
            loginViewModel.login(loginEmail.text.toString(), loginPassword.text.toString())
                .observe(viewLifecycleOwner) {
                    when (it) {
                        is DataResult.Loading -> {
                            loading.isVisible = true
                        }
                        is DataResult.Empty -> {
                            loginEmail.error = "Sem retorno"
                            loginPassword.error = "Sem retorno"
                        }
                        is DataResult.Success -> {
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToProfileActivity())
                        }
                        else -> {}
                    }
                }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
