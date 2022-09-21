package com.example.googlemap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.googlemap.R
import com.example.googlemap.databinding.FragmentHomeBinding
import com.example.googlemap.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentHomeBinding = FragmentHomeBinding.bind(view)
        homeViewModel = ViewModelProvider(this@HomeFragment).get(HomeViewModel::class.java)
        fragmentHomeBinding.homeviewmodel = homeViewModel
        fragmentHomeBinding.lifecycleOwner = this@HomeFragment
    }
}