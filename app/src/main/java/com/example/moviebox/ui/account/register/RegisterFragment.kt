package com.example.moviebox.ui.account.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviebox.databinding.FragmentRegisterBinding
import com.example.moviebox.ui.main.MainActivity


class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.btnSubmit.setOnClickListener {
            val intent = Intent(activity,MainActivity::class.java)
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
        return binding.root
    }

}