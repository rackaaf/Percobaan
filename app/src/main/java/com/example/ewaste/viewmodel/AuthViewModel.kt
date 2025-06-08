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
    private val repository: AuthRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var loginSuccess by mutableStateOf(false)
    var registerMessage by mutableStateOf<String?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    // Tambahan state untuk navigasi
    var otpSent by mutableStateOf(false)
    var otpVerified by mutableStateOf(false)

    var otpVerifiedForForgot by mutableStateOf(false)
    var passwordResetSuccess by mutableStateOf(false)

    // Login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.login(LoginRequest(email, password))
            isLoading = false
            if (result.isSuccess) {
                loginSuccess = true
            } else {
                errorMessage = result.exceptionOrNull()?.message
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
                registerMessage = result.getOrNull() ?: "Register berhasil. Periksa email untuk OTP."
                otpSent = true
            } else {
                registerMessage = result.exceptionOrNull()?.message ?: "Gagal register"
                otpSent = false
            }
        }
    }

//    fun verifyOtp(email: String, code: String) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.verifyOtp(OtpRequest(email, code))
//            isLoading = false
//
//            if (result.isSuccess) {
//                registerMessage = result.getOrNull() ?: "OTP berhasil diverifikasi"
//                otpVerified = true
//            } else {
//                registerMessage = result.exceptionOrNull()?.message ?: "OTP salah"
//                otpVerified = false
//            }
//        }
//    }

    fun verifyOtp(code: String, purpose: String = "register") {
        viewModelScope.launch {
            isLoading = true
            val result = repository.verifyOtp(OtpRequest(code))
            isLoading = false

            if (result.isSuccess) {
                registerMessage = result.getOrNull() ?: "OTP berhasil diverifikasi"

                when (purpose) {
                    "register" -> otpVerified = true
                    "forgot" -> otpVerifiedForForgot = true
                }
            } else {
                registerMessage = result.exceptionOrNull()?.message ?: "OTP salah"
            }
        }
    }



    fun forgotPassword(email: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.forgotPassword(email)
            isLoading = false
            // Pastikan result.getOrNull() mengembalikan objek yang memiliki message
            registerMessage = result.getOrNull() ?: "Cek email untuk OTP reset."  // Fallback message jika gagal
        }
    }

    // Reset password
//    fun resetPassword(email: String, otp: String, newPassword: String) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.resetPassword(email, otp, newPassword)
//            isLoading = false
//            // Pastikan result.getOrNull() mengembalikan objek yang memiliki message
//            registerMessage = result.getOrNull() ?: "Password berhasil direset."  // Fallback message jika gagal
//        }
//    }

    // Fungsi reset password hanya dengan password baru
    fun resetPassword(newPassword: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.resetPassword(newPassword) // Menggunakan hanya password baru
            isLoading = false

            passwordResetSuccess = result.isSuccess
            registerMessage = result.getOrNull() ?: result.exceptionOrNull()?.message ?: "Terjadi kesalahan"
        }
    }



    // Update profile
    fun updateProfile(name: String, address: String, birthDate: String, photoUrl: String?) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.updateProfile(ProfileRequest(name, address, birthDate, photoUrl))
            isLoading = false
            registerMessage = result.getOrNull() ?: "Profil gagal diperbarui."
        }
    }

    // Update password
    fun updatePassword(oldPass: String, newPass: String, confirmPass: String) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.updatePassword(UpdatePasswordRequest(oldPass, newPass, confirmPass))
            isLoading = false
            registerMessage = result.getOrNull() ?: "Password gagal diperbarui."
        }
    }
}



//package com.example.ewaste.viewmodel
//
//import androidx.compose.runtime.*
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.ewaste.data.remote.model.*
//import com.example.ewaste.data.repository.AuthRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class AuthViewModel @Inject constructor(
//    private val repository: AuthRepository
//) : ViewModel() {
//
//    var isLoading by mutableStateOf(false)
//    var loginSuccess by mutableStateOf(false)
//    var registerMessage by mutableStateOf<String?>(null)
//    var errorMessage by mutableStateOf<String?>(null)
//
//    fun login(email: String, password: String) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.login(LoginRequest(email, password))
//            isLoading = false
//            loginSuccess = result.isSuccess
//            errorMessage = result.exceptionOrNull()?.message
//        }
//    }
//
//    fun register(username: String, email: String, phone: String, password: String) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.register(RegisterRequest(username, email, phone, password))
//            isLoading = false
//            registerMessage = if (result.isSuccess) {
//                result.getOrNull()?.message() ?: "Register berhasil. Periksa email untuk OTP."
//            } else {
//                result.exceptionOrNull()?.message ?: "Gagal register"
//            }
//        }
//    }
//
//    fun verifyOtp(email: String, code: String) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.verifyOtp(OtpRequest(email, code))
//            isLoading = false
//            registerMessage = if (result.isSuccess) {
//                result.getOrNull()?.message() ?: "OTP berhasil diverifikasi"
//            } else {
//                result.exceptionOrNull()?.message ?: "OTP salah"
//            }
//        }
//    }
//
//    fun forgotPassword(email: String) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.forgotPassword(email)
//            isLoading = false
//            registerMessage = result.getOrNull()?.message() ?: "Cek email untuk OTP reset."
//        }
//    }
//
//    fun resetPassword(email: String, otp: String, newPassword: String) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.resetPassword(email, otp, newPassword)
//            isLoading = false
//            registerMessage = result.getOrNull()?.message() ?: "Password berhasil direset."
//        }
//    }
//
//    fun updateProfile(name: String, address: String, birthDate: String, photoUrl: String?) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.updateProfile(ProfileRequest(name, address, birthDate, photoUrl))
//            isLoading = false
//            registerMessage = result.getOrNull()?.message() ?: "Profil diperbarui."
//        }
//    }
//
//    fun updatePassword(oldPass: String, newPass: String, confirmPass: String) {
//        viewModelScope.launch {
//            isLoading = true
//            val result = repository.updatePassword(UpdatePasswordRequest(oldPass, newPass, confirmPass))
//            isLoading = false
//            registerMessage = result.getOrNull()?.message() ?: "Password berhasil diubah."
//        }
//    }
//
//}
