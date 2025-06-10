package com.example.ewaste.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ewaste.data.repository.AuthRepository
import com.example.ewaste.data.remote.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val repository: AuthRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var loginSuccess by mutableStateOf(false)
    var registerMessage by mutableStateOf<String?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    // State untuk navigasi
    var otpSent by mutableStateOf(false)
    var otpVerified by mutableStateOf(false)
    var otpVerifiedForForgot by mutableStateOf(false)
    var passwordResetSuccess by mutableStateOf(false)

    // State untuk forgot password flow
    private var forgotPasswordEmail by mutableStateOf("")

    // Login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            val result = repository.login(LoginRequest(email, password))
            isLoading = false

            if (result.isSuccess) {
                loginSuccess = true
                errorMessage = null
            } else {
                errorMessage = result.exceptionOrNull()?.message
                loginSuccess = false
            }
        }
    }

    // Register
    fun register(username: String, email: String, phone: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.register(RegisterRequest(username, email, phone, password))
            isLoading = false

            if (result.isSuccess) {
                registerMessage = result.getOrNull()
                otpSent = true
            } else {
                registerMessage = result.exceptionOrNull()?.message
                otpSent = false
            }
        }
    }

    // Verify OTP
    fun verifyOtp(code: String, purpose: String = "register") {
        viewModelScope.launch {
            isLoading = true
            val result = repository.verifyOtp(OtpRequest(code))
            isLoading = false

            if (result.isSuccess) {
                registerMessage = result.getOrNull()
                when (purpose) {
                    "register" -> otpVerified = true
                    "forgot" -> otpVerifiedForForgot = true
                }
            } else {
                registerMessage = result.exceptionOrNull()?.message
            }
        }
    }

    // Forgot password
    fun forgotPassword(email: String) {
        viewModelScope.launch {
            isLoading = true
            forgotPasswordEmail = email // Save email for later use
            val result = repository.forgotPassword(email)
            isLoading = false

            registerMessage = if (result.isSuccess) {
                result.getOrNull()
            } else {
                result.exceptionOrNull()?.message
            }
        }
    }

    // Reset password (updated to include email and OTP)
    fun resetPassword(otpCode: String, newPassword: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.resetPassword(forgotPasswordEmail, otpCode, newPassword)
            isLoading = false

            passwordResetSuccess = result.isSuccess
            registerMessage = result.getOrNull() ?: result.exceptionOrNull()?.message
        }
    }

    // Update profile
    fun updateProfile(name: String, address: String, birthDate: String, photoUrl: String? = null) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.updateProfile(ProfileRequest(name, address, birthDate, photoUrl))
            isLoading = false
            registerMessage = result.getOrNull() ?: result.exceptionOrNull()?.message
        }
    }

    // Update password
    fun updatePassword(oldPass: String, newPass: String, confirmPass: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.updatePassword(UpdatePasswordRequest(oldPass, newPass, confirmPass))
            isLoading = false
            registerMessage = result.getOrNull() ?: result.exceptionOrNull()?.message
        }
    }

    // Logout
    fun logout() {
        viewModelScope.launch {
            repository.logout()
            // Reset all states
            loginSuccess = false
            otpSent = false
            otpVerified = false
            otpVerifiedForForgot = false
            passwordResetSuccess = false
            registerMessage = null
            errorMessage = null
        }
    }

    // Reset states
    fun resetStates() {
        loginSuccess = false
        otpSent = false
        otpVerified = false
        otpVerifiedForForgot = false
        passwordResetSuccess = false
        registerMessage = null
        errorMessage = null
    }
}