package com.example.ecommerceapp.ui.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.ui.data.repository.AuthRepository
import com.example.ecommerceapp.ui.utils.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    internal val _authResult = Channel<AuthResult>()
    val authResult = _authResult.receiveAsFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        if (email.isBlank() || password.isBlank()) {
            _authResult.send(AuthResult.Error("Email and password cannot be empty"))
            return@launch
        }

        _authResult.send(AuthResult.Loading)
        val user = repository.login(email, password)
        if (user != null) {
            _authResult.send(AuthResult.Success(user))
        } else {
            _authResult.send(AuthResult.Error("Authentication failed"))
        }
    }

    fun register(email: String, password: String) = viewModelScope.launch {
        if (email.isBlank() || password.isBlank()) {
            _authResult.send(AuthResult.Error("Email and password cannot be empty"))
            return@launch
        }

        _authResult.send(AuthResult.Loading)
        val user = repository.register(email, password)
        if (user != null) {
            _authResult.send(AuthResult.Success(user))
        } else {
            _authResult.send(AuthResult.Error("Registration failed"))
        }
    }

    fun handleGoogleSignIn(token: String) = viewModelScope.launch {
        _authResult.send(AuthResult.Loading)
        repository.googleSignIn(token)?.let { user ->
            _authResult.send(AuthResult.Success(user))
        } ?: _authResult.send(AuthResult.Error("Google sign-in failed"))
    }
}