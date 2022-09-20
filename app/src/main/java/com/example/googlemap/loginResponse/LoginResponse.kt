package com.example.googlemap.loginResponse
import com.example.example.Result
import com.google.gson.annotations.SerializedName


data class LoginResponse (
  @SerializedName("success" ) var success : Int?    = null,
  @SerializedName("message" ) var message : String? = null,
  @SerializedName("result"  ) var result  : Result? = Result()

)