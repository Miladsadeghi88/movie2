package com.example.moviebox.ui.account.register

import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebox.room.entityUser.EntityUserTable
import com.example.moviebox.ui.account.userRepository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.isBlank

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _usernameExists = MutableStateFlow<Boolean>(false)
    val usernameExists: StateFlow<Boolean> = _usernameExists.asStateFlow()

    private val _usernameError = MutableStateFlow<String?>(null)
    val usernameError: StateFlow<String?> = _usernameError.asStateFlow()

    private val _phoneNumberExists = MutableStateFlow<Boolean>(false)
    val phoneNumberExists: StateFlow<Boolean> = _phoneNumberExists.asStateFlow()

    private val _phoneNumberError = MutableStateFlow<String?>(null)
    val phoneNumberError: StateFlow<String?> = _phoneNumberError.asStateFlow()

    private val _emailExists = MutableStateFlow<Boolean>(false)
    val emailExists: StateFlow<Boolean> = _emailExists.asStateFlow()

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()

    private val _repeatPasswordError = MutableStateFlow<String?>(null)
    val repeatPasswordError: StateFlow<String?> = _repeatPasswordError.asStateFlow()

    private val _registrationStatus = MutableSharedFlow<RegistrationStatus>()
    val registrationStatus: SharedFlow<RegistrationStatus> = _registrationStatus.asSharedFlow()

    fun checkUsername(username: String) {
        viewModelScope.launch {
            repository.checkUsernameExists(username).collect { exists ->
                _usernameExists.value = exists
                if (exists) {
                    _usernameError.value = "Username already exists"
                } else { // Combine length check here
                    _usernameError.value = if (username.length < 10) {
                        "Username must be at least 10 characters"
                    } else {
                        null
                    }
                }
            }
        }
    }

    fun registerUser(user: EntityUserTable) {
        viewModelScope.launch {
            _registrationStatus.emit(RegistrationStatus.InProgress)
            if (allFieldsValid(user) && noDuplicatesFound(user)) {
                repository.insertUser(user)
                _registrationStatus.emit(RegistrationStatus.Success)
            } else {
                _registrationStatus.emit(RegistrationStatus.Failure)
            }
        }
    }

    private fun allFieldsValid(user: EntityUserTable): Boolean {
        // Implement your field validation logic here, including password checks
        if (user.username.isBlank()) {
            _usernameError.value = "Required"
            return false
        } else if (user.username.length in 1..9 || user.username.length > 20) {
            _usernameError.value = "Username must be between 10 to 20 characters"
            return false
        } else {
            _usernameError.value = null
        }

        if (user.email.isBlank()) {
            _emailError.value = "Required"
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            _emailError.value = "Invalid email address"
            return false
        } else {
            _emailError.value = null
        }

        if (user.phoneNumber == 0L) {
            _phoneNumberError.value = "Required"
            return false
        } else if (!user.phoneNumber.toString().startsWith("9") || user.phoneNumber.toString().length != 10) {
            _phoneNumberError.value = "Invalid mobile number"
            return false
        } else {
            _phoneNumberError.value = null
        }

        if (user.password.isBlank()) {
            _passwordError.value = "Required"
            return false
        } else if (user.password.length < 8 || !user.password.any { it.isDigit() } || !user.password.any { it.isUpperCase() } || !user.password.any { !it.isLetterOrDigit() }) {
            _passwordError.value = "Password must be at least 8 characters long and contain at least one digit, one uppercase letter, and one special character"
            return false
        } else {
            _passwordError.value = null
        }

        return !usernameExists.value && !emailExists.value && !phoneNumberExists.value
    }

    private fun noDuplicatesFound(user: EntityUserTable): Boolean {
        return !usernameExists.value && !emailExists.value && !phoneNumberExists.value
    }

    fun checkRepeatPassword(password: String, repeatPassword: String) {
        if (repeatPassword.isBlank()) {
            _repeatPasswordError.value = "Required"
        } else if (password != repeatPassword) {
            _repeatPasswordError.value = "Passwords do not match"
        } else {
            _repeatPasswordError.value = null
        }
    }

    sealed class RegistrationStatus {
        object Success : RegistrationStatus()
        object Failure : RegistrationStatus()
        object InProgress : RegistrationStatus()
    }
}