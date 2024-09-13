package com.example.moviebox.ui.account.forgotPass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviebox.databinding.FragmentForgotPassBinding

class ForgotPassFragment :Fragment(){
    private lateinit var binding: FragmentForgotPassBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPassBinding.inflate(inflater,container,false)
        return binding.root
    }
}