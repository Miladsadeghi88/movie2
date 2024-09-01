package com.example.moviebox.ui.account.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviebox.databinding.FragmentIntroBinding

class IntroFragment:Fragment() {
   private lateinit var binding: FragmentIntroBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentIntroBinding.inflate(inflater, container,false)
        return binding.root
    }
}