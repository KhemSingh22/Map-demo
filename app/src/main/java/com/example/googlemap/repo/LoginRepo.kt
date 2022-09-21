package com.example.googlemap.repo
import com.example.googlemap.loginResponse.LoginResponse
import com.example.googlemap.network.RetrofitClient
import com.example.googlemap.network.RetrofitService
import retrofit2.Response

class LoginRepo() {

    private var api = RetrofitClient.getService().create(RetrofitService::class.java)
    suspend fun login(emailPhone: String, pasword: String): Response<LoginResponse> {

        return api!!.loginUser(emailPhone, pasword, "", "android")

        /* try {
     loginresponse!!.value=""
    api= RetrofitClient.getService().create(RetrofitService::class.java)

     GlobalScope.launch(Dispatchers.Default){
         bodyData=api.loginUser(emailPhone, pasword, "", "android")

         Log.e("SDSDSDSDS",bodyData.body().toString())
         loginresponse!!.value =bodyData.body().toString()

         *//*if (bodyData.body() != null) {
                        Log.d("DONE: ",bodyData.body().toString())
                        loginresponse!!.value=bodyData.body()

                    } else {
                        Log.d("NOT--DONE: ",bodyData.message().toString())
                    }*//*
                }

            } catch (e: Exception) {
                println(e)
            }*/

    }

}