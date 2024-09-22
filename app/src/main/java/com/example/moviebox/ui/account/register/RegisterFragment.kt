package com.example.moviebox.ui.account.register
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.launch
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.text
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.moviebox.databinding.FragmentRegisterBinding
import com.example.moviebox.room.entityUser.EntityUserTable
import com.example.moviebox.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.text.toLongOrNull

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: RegisterViewModel by viewModels()

        binding.usernameEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.checkUsername(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // ... similar TextWatchers for email, phoneNumber, password

        binding.repeatPasswordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.checkRepeatPassword(
                    binding.passwordEditText.text.toString(),
                    s.toString()
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnSubmit.setOnClickListener {
            val user = EntityUserTable(
                0, // You might need to adjust this based on your EntityUserTable structure
                binding.usernameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.phoneNumberEditText.text.toString().toLongOrNull() ?: 0L,
                binding.passwordEditText.text.toString()
            )
            viewModel.registerUser(user)
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registrationStatus.collect { status ->
                    when (status) {
                        RegisterViewModel.RegistrationStatus.Success -> {
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            requireActivity().finish()
                        }
                        RegisterViewModel.RegistrationStatus.Failure -> {
                            Snackbar.make(binding.root, "Registration failed. Please check your input.", Snackbar.LENGTH_SHORT).show()
                        }
                        RegisterViewModel.RegistrationStatus.InProgress -> {
                            // You can show a progress indicator here if needed
                        }
                    }
                }
                viewModel.usernameError.collect { error ->
                    binding.usernameTextInputLayout.error = error
                }
                // ... similar collectors for other error StateFlows (or use a sealed class for errors)
            }




}
