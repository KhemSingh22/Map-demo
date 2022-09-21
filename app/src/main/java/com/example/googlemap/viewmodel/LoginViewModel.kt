package com.example.googlemap.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.googlemap.loginResponse.LoginResponse
import com.example.googlemap.network.RetrofitService
import com.example.googlemap.repo.LoginRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var bodyData: Response<LoginResponse>
    private lateinit var api: RetrofitService

    var Email_phone = ObservableField("")
    var pass = ObservableField("")

    //    private var loginListner : LoginListner? = null
    var loginResponse = MutableLiveData<LoginResponse>()
    var error = MutableLiveData<String>()

    fun login(view : View) {
        if (Email_phone.get().isNullOrEmpty() || pass.get().toString().isEmpty()) {
            Toast.makeText(
                getApplication(),
                "Email and Password filed is required ",
                Toast.LENGTH_SHORT
            ).show()
        } else {

            CoroutineScope(Dispatchers.Main).launch {
                bodyData = LoginRepo().login(Email_phone.get().toString(), pass.get().toString())
                if (bodyData.isSuccessful) {
                    loginResponse.value = bodyData.body()
                }else{
                    Toast.makeText(
                        getApplication(),
                        "Server error ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


/*
            GlobalScope.launch(Dispatchers.Main) {
                //val loginRepo: LiveData<LoginResponse>? = LoginRepo().login(Email_phone.get().toString(), pass.get().toString())
                api = RetrofitClient.getService().create(RetrofitService::class.java)
                bodyData = api.loginUser(
                    Email_phone.get().toString(),
                    pass.get().toString(),
                    "",
                    "android"
                )

                if (bodyData.isSuccessful) {
                    response.value = bodyData.body()
                } else {
                    error.value = bodyData.message().toString()
                }

            }
*/

        }
    }

    fun register(view: View) {

    }
}