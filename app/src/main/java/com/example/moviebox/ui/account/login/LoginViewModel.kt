package com.example.moviebox.ui.account.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebox.room.entityUser.EntityUserTable
import com.example.moviebox.ui.account.userRepository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository)
    :ViewModel(){
private val _loginste= MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: MutableStateFlow<LoginState> = _loginste

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginste.value = LoginState.Loading
            val user = userRepository.getUserByUsername(username)
                .collect {
                    if (it != null && it.password == password) {
                        _loginste.value = LoginState.Success(it)
                    } else {
                        _loginste.value = LoginState.Error("Invalid username or password")
                    }
                }

        }


}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: EntityUserTable) : LoginState()
    data class Error(val message: String) : LoginState()
}
}