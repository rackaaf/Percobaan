package com.example.ewaste.data.remote.model

data class UpdatePasswordRequest(
    val oldPassword: String,
    val newPassword: String,
    val confirmPassword: String
)
