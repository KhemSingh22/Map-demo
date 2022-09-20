package com.example.googlemap.network

import com.example.googlemap.loginResponse.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface RetrofitService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("emailOrPhone") emailOrPhone: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String,
        @Field("device_type") device_type: String
    ): Response<LoginResponse>

}

