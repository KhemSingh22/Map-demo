package com.example.googlemap.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation
import com.example.googlemap.R

class HomeViewModel(application: Application):AndroidViewModel(application) {
    fun goProfile(view: View){
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_profileFragment)
    }

    fun goSAved(view: View){
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_savedFragment)
    }
}
