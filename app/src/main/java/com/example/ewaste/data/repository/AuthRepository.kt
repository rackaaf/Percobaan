package com.example.ewaste.data.repository

import com.example.ewaste.data.remote.model.*
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepository @Inject constructor(){

    // Data dummy pengguna
    private val dummyUsers = listOf(
        User("admin@example.com", "password123", "adminUser"),
        User("user@example.com", "password123", "testUser")
    )

    data class User(val email: String, val password: String, val username: String)

    // Fungsi login menggunakan data dummy
    suspend fun login(request: LoginRequest): Result<String> {
        delay(1000)  // Simulasi delay jaringan
        val user = dummyUsers.find { it.email == request.email && it.password == request.password }
        return if (user != null) {
            Result.success("Login successful")  // Login berhasil
        } else {
            Result.failure(Exception("Invalid email or password"))  // Login gagal
        }
    }

    // Fungsi register menggunakan data dummy
    suspend fun register(request: RegisterRequest): Result<String> {
        delay(1000)  // Simulasi delay jaringan
        val newUser = User(request.email, request.password, request.username)
        // Simulasi data berhasil disimpan
        return Result.success("User registered successfully. Check your email for OTP.")
    }

    suspend fun verifyOtp(request: OtpRequest): Result<String> {
        delay(1000)  // Simulasi delay jaringan
        // Simulasi pengecekan OTP
        val isValidOtp = request.code == "123456"  // Cek kode OTP dari dummy

        return if (isValidOtp) {
            // Jika OTP valid, kembalikan sukses dengan pesan
            Result.success("OTP berhasil diverifikasi")
        } else {
            // Jika OTP salah, kembalikan failure dengan pesan error
            Result.failure(Exception("OTP salah"))
        }
    }



    // Fungsi forgot password dengan data dummy
    suspend fun forgotPassword(email: String): Result<String> {
        delay(1000)  // Simulasi delay jaringan
        val user = dummyUsers.find { it.email == email }
        return if (user != null) {
            Result.success("OTP sent successfully")  // OTP berhasil dikirim
        } else {
            Result.failure(Exception("Email not found"))  // Email tidak ditemukan
        }
    }

    // Fungsi reset password dengan data dummy (diubah untuk hanya menerima newPassword)
    suspend fun resetPassword(newPassword: String): Result<String> {
        delay(1000)  // Simulasi delay jaringan
        // Simulasi reset password berhasil
        return Result.success("Password reset successful")
    }


    // Fungsi update profile dengan data dummy
    suspend fun updateProfile(request: ProfileRequest): Result<String> {
        delay(1000)  // Simulasi delay jaringan
        // Simulasi profile berhasil diperbarui
        return Result.success("Profile updated successfully")
    }

    // Fungsi update password dengan data dummy
    suspend fun updatePassword(request: UpdatePasswordRequest): Result<String> {
        delay(1000)  // Simulasi delay jaringan
        // Simulasi password berhasil diperbarui
        return Result.success("Password updated successfully")
    }
}



//import com.example.ewaste.data.remote.ApiService
//import com.example.ewaste.data.remote.model.*
//import javax.inject.Inject
//
//class AuthRepository @Inject constructor(
//    private val api: ApiService
//) {
//    suspend fun login(request: LoginRequest) = runCatching {
//        api.login(request)
//    }
//
//    suspend fun register(request: RegisterRequest) = runCatching {
//        api.register(request)
//    }
//
//    suspend fun verifyOtp(request: OtpRequest) = runCatching {
//        api.verifyOtp(request)
//    }
//
//    suspend fun forgotPassword(email: String) = runCatching {
//        api.forgotPassword(ForgotPasswordRequest(email))
//    }
//
//    suspend fun resetPassword(email: String, otp: String, newPassword: String) = runCatching {
//        api.resetPassword(ResetPasswordRequest(email, otp, newPassword))
//    }
//
//    suspend fun updateProfile(request: ProfileRequest) = runCatching {
//        api.updateProfile(request)
//    }
//
//    suspend fun updatePassword(request: UpdatePasswordRequest) = runCatching {
//        api.updatePassword(request)
//    }
//
//}
