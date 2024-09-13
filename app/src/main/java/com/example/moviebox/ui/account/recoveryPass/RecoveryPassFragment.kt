package com.example.moviebox.ui.account.recoveryPass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviebox.databinding.FragmentRecoveryPassBinding

class RecoveryPassFragment:Fragment() {
    private lateinit var binding: FragmentRecoveryPassBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRecoveryPassBinding.inflate(inflater,container,false)
        return binding.root
    }
}