package com.omniwyse.petcaremobileapp.services

import com.omniwyse.petcaremobileapp.model.LoginData
import com.omniwyse.petcaremobileapp.model.LoginResponse
import com.omniwyse.petcaremobileapp.model.Services
import com.omniwyse.petcaremobileapp.model.Users
import retrofit2.Call
import retrofit2.http.*

public interface PetcareServices {
    @GET("displayAll")
    fun getServices(): Call<List<Services>>
    @POST("register")
    fun register(@Body info: Users):Call<RegisterResponse>
    @POST("login")
    fun login(@Body info: LoginData):Call<LoginResponse>
}