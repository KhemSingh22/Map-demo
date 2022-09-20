package com.example.googlemap.factries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.googlemap.activities.LoginActivity
import com.example.googlemap.viewmodel.LoginViewModel

class LoginViewModelFactory(val activity: LoginActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)

//        return LoginViewModel(activity) as T

    }
}