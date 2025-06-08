package com.example.ewaste.data.remote.model

data class ResetPasswordRequest(
    val email: String,
    val otpCode: String,
    val newPassword: String
)
