package com.example.googlemap.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.googlemap.R
import com.example.googlemap.databinding.FragmentProfileBinding
import com.example.googlemap.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    lateinit var profile_binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile_binding = FragmentProfileBinding.bind(view)
        profileViewModel = ViewModelProvider(this@ProfileFragment).get(ProfileViewModel::class.java)
        profile_binding.profileviewmodel = profileViewModel
        profile_binding.lifecycleOwner = this@ProfileFragment

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        setupDrawerLayout()

    }

    private fun setupDrawerLayout() {
//        profile_binding.navView.setupWithNavController(navController)
//        NavigationUI.setupActionBarWithNavController(activity, navController, profile_binding.drawerLayout)
    }

}