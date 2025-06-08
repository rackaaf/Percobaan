package com.example.ewaste.data.remote.model

data class UserResponse(
    val success: Boolean,
    val data: UserDto
)

data class UserDto(
    val id: Int,
    val username: String,
    val email: String
)
