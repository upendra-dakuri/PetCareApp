package com.omniwyse.petcaremobileapp.services

import com.omniwyse.petcaremobileapp.model.*
import retrofit2.Call
import retrofit2.http.*

public interface PetcareServices {
    @GET("displayAll")
    fun getServices(): Call<List<Services>>
    @POST("register")
    fun register(@Body info: Users):Call<RegisterResponse>
    @POST("login")
    fun login(@Body info: LoginData):Call<LoginResponse>
    @POST("reset-password")
    fun reSettingPassword(@Body info: FPasswordBody):Call<FPasswordResponse>
}