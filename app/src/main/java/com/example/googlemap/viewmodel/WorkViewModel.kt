package com.example.googlemap.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WorkViewModel(application: Application) : AndroidViewModel(application) {
    val periodic_data = MutableLiveData<String>()
    val oneTimeWorkData = MutableLiveData<String>()
    private val time = MutableLiveData<Int>()
    private val oneTimeWorkDescription = MutableLiveData<String>()

    fun setPeriodicWorkButtonClicked() {
        if (periodic_data.equals("")){
            Toast.makeText(getApplication(),"Field is required ",Toast.LENGTH_SHORT).show()
        }else{
            time.value = periodic_data.value?.toInt()
        }
    }

    fun setPeriodWork() : LiveData<Int> {
        return time
    }

    fun setOneTimeWorkButtonClicked() {
        if (oneTimeWorkData.equals("")){
            Toast.makeText(getApplication(),"Field is required ",Toast.LENGTH_SHORT).show()

        }else{
            oneTimeWorkDescription.value = oneTimeWorkData.value
        }
    }

    fun setOneTimeWork() : LiveData<String> {
        return oneTimeWorkDescription
    }
}