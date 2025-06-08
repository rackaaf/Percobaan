package com.example.ewaste.data.remote.model

data class ProfileRequest(
    val name: String,
    val address: String,
    val birthDate: String,
    val photoUrl: String? = null
)
