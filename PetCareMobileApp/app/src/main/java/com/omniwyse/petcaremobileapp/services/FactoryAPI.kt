package com.omniwyse.petcaremobileapp.services
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object FactoryAPI {
        private const val URL="http://10.0.2.2:5000/api/users/"
        // Create OkHttp Client
        private val okHttp = OkHttpClient.Builder().connectTimeout(3,
    java.util.concurrent.TimeUnit.SECONDS
    )
                    /*private val okHttp = OkHttpClient.Builder().connectTimeout(10,
                java.util.concurrent.TimeUnit.SECONDS*/

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