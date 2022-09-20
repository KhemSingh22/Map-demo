package com.example.googlemap.activities

import PeriodicBackgroundNotification
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.googlemap.R
import com.example.googlemap.databinding.ActivityWorkBinding
import com.example.googlemap.utils.MyWork
import java.util.concurrent.TimeUnit

class WorkActivity : AppCompatActivity() {

    @SuppressLint("EnqueueWork")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)

        val binding: ActivityWorkBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_work)

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val request1 = OneTimeWorkRequestBuilder<MyWork>().setConstraints(constraints).build()
        val request = PeriodicWorkRequestBuilder<MyWork>(20, TimeUnit.MINUTES).setConstraints(constraints).build()

        binding.oneTime.setOnClickListener {
            WorkManager.getInstance(getApplication()).enqueue(request1)
        }

        binding.periodic.setOnClickListener {
            WorkManager.getInstance(getApplication()).enqueue(request)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request1.id)
            .observe(this, Observer {
                val data_ID = it.state.name
                Log.e("SSSSS", it.state.toString())
                Toast.makeText(this, data_ID, Toast.LENGTH_SHORT).show()
            })
    }

}