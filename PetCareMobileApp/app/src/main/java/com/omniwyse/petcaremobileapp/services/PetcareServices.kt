package com.omniwyse.petcaremobileapp.services

import com.omniwyse.petcaremobileapp.model.Services
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

public interface PetcareServices {
   @GET("displayAll")
    fun getServices(): Call<List<Services>>

    @GET
    fun getMessage(@Url anotherUrl: String): Call<String>
/*
    @POST("api/services/register")
    fun getServices(): Call<List<Services>>*/

}