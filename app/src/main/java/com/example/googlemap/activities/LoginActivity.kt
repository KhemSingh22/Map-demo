package com.example.googlemap.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.googlemap.R
import com.example.googlemap.databinding.ActivityLoginBinding
import com.example.googlemap.listners.LoginListner
import com.example.googlemap.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(),LoginListner {
    lateinit var loginViewModel: LoginViewModel
    lateinit var homeBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this@LoginActivity).get(LoginViewModel::class.java)
        homeBinding.lviewmodel = loginViewModel
        homeBinding.setLifecycleOwner(this@LoginActivity)
        succes()
        faild()
    }

    private fun faild() {
        loginViewModel.error.observe(this, Observer {
            homeBinding.progressBar.visibility = View.GONE
            Toast.makeText(
                this@LoginActivity,
                "Email and Password filed is required ",
                Toast.LENGTH_SHORT
            ).show()
        })

    }

    private fun succes() {
        homeBinding.progressBar.visibility = View.GONE
        loginViewModel.loginResponse.observe(this, Observer {
            Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,WorkActivity::class.java))
            finish()
        })
    }

    override fun start() {
        homeBinding.progressBar.visibility = View.VISIBLE
    }
}