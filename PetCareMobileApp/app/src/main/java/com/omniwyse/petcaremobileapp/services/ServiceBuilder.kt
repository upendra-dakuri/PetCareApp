package com.omniwyse.petcaremobileapp.services

import android.icu.util.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import java.net.URL
import javax.xml.datatype.DatatypeConstants.SECONDS

object ServiceBuilder {
    private const val URL="http://10.0.2.2:5000/api/services/"
    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder().connectTimeout(10,
        java.util.concurrent.TimeUnit.SECONDS
    )
        .readTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(10, java.util.concurrent.TimeUnit.SECONDS)

    // Create Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // Create Retrofit Instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}