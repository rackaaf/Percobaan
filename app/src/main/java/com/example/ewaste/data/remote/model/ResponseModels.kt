package com.example.ewaste.data.remote.model

data class BaseResponse(
    val success: Boolean,
    val message: String? = null
)

data class UserResponse(
    val success: Boolean,
    val message: String? = null,
    val data: UserDto
)

data class UserDto(
    val id: Int,
    val username: String,
    val email: String,
    val token: String? = null
)

data class KategoriResponse(
    val id: Int,
    val nama: String,
    val deskripsi: String? = null
)

data class JenisResponse(
    val id: Int,
    val namaJenis: String,
    val kategoriId: Int
)