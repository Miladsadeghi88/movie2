package com.example.moviebox.ui.account

import android.accounts.Account
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviebox.databinding.ActivityAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAccountBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}