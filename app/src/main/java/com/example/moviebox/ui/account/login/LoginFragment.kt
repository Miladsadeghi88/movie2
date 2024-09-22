package com.example.moviebox.ui.account.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.moviebox.R
import com.example.moviebox.databinding.FragmentLoginBinding
import com.example.moviebox.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel :LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtDoNotHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.txtRegisterNow.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.txtForgotPass.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment)
        }
        binding.btnLogin.setOnClickListener {
            binding.layLoading.isVisible = true
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(username, password)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collect { state ->
                    binding.layLoading.isVisible = false
                    when (state) {
                        is LoginViewModel.LoginState.Loading -> {
                            // Show loading indicator (e.g., ProgressBar)
                        }
                        is LoginViewModel.LoginState.Success -> {
                            val intent = Intent(activity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                            requireActivity().finish()
                        }
                        is LoginViewModel.LoginState.Error -> {
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> {}
                    }
                }
            }
        }

    }


}