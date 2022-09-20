package com.example.googlemap.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
     const val BASE_URL = "https://api.enomy.com/api/"
    fun getService(): Retrofit{
        return Retrofit.Builder()
           .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        getService().create(RetrofitService::class.java)

    }
}