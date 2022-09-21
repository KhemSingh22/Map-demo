package com.example.googlemap.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation
import com.example.googlemap.R

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    fun gohome(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_homeFragment)
    }

    fun goSave(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_savedFragment)
    }
}