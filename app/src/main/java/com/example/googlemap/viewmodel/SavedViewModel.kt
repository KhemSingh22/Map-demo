package com.example.googlemap.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation
import com.example.googlemap.R

class SavedViewModel(application: Application) :AndroidViewModel(application) {

    fun gohome(view: View){
        Navigation.findNavController(view).navigate(R.id.action_savedFragment_to_homeFragment)
    }

    fun goprofile(view: View){
        Navigation.findNavController(view).navigate(R.id.action_savedFragment_to_profileFragment)
    }
}