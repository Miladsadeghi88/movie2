package com.example.moviebox.ui.account.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviebox.R
import com.example.moviebox.databinding.FragmentIntroBinding

class IntroFragment:Fragment() {
   private lateinit var binding: FragmentIntroBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentIntroBinding.inflate(inflater, container,false)
        binding.btnGetIn.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_loginFragment)
        }
        return binding.root
    }
}