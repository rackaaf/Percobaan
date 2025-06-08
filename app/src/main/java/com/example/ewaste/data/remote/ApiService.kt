package com.example.ewaste.data.remote

import com.example.ewaste.data.remote.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<UserResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<BaseResponse>

    @POST("verify-otp")
    suspend fun verifyOtp(@Body request: OtpRequest): Response<BaseResponse>

    @POST("forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<BaseResponse>

    @POST("reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<BaseResponse>

    @POST("update-profile")
    suspend fun updateProfile(@Body request: ProfileRequest): Response<BaseResponse>

    @POST("update-password")
    suspend fun updatePassword(@Body request: UpdatePasswordRequest): Response<BaseResponse>

    @GET("kategori")
    suspend fun getKategori(): Response<List<KategoriResponse>>

    @GET("jenis")
    suspend fun getJenis(): Response<List<JenisResponse>>

}
