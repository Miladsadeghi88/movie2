package com.example.moviebox.ui.account.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviebox.R
import com.example.moviebox.databinding.FragmentLoginBinding
import com.example.moviebox.ui.account.AccountActivity
import com.example.moviebox.ui.main.MainActivity

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

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
         val intent = Intent(activity,MainActivity::class.java)
         Intent.FLAG_ACTIVITY_CLEAR_TASK
         startActivity(intent)
         requireActivity().finish()
     }
        return binding.root
    }
}