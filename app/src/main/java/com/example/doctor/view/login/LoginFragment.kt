package com.example.doctor.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.doctor.R
import com.example.doctor.databinding.FragmentLoginBinding
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
        loginViewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                loginEmail.error = "Erro de login!"
            } else {
                loginEmail.error = null
            }
        }

        val loginPassword = binding.loginPassword
        loginViewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                loginPassword.error = "Erro de login!"
            } else {
                loginPassword.error = null
            }
        }

        loginViewModel.success.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        val btnForgot = binding.btnForgot
        btnForgot.setOnClickListener {
            dialog.show(parentFragmentManager, ForgotPassDialogFragment.TAG)
        }

        val btnLogin = binding.btnLogin
        btnLogin.setOnClickListener {
            loginViewModel.login(loginEmail.text.toString(), loginPassword.text.toString())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
