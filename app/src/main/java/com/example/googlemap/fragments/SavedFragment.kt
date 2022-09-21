package com.example.googlemap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.googlemap.R
import com.example.googlemap.databinding.FragmentSavedBinding
import com.example.googlemap.viewmodel.SavedViewModel

class SavedFragment : Fragment() {

    private var fragmentSavedBinding: FragmentSavedBinding? = null
lateinit var savedViewModel: SavedViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSavedBinding.bind(view)
        fragmentSavedBinding = binding
        savedViewModel = ViewModelProvider(this@SavedFragment).get(SavedViewModel::class.java)
        binding.savedviewmodel = savedViewModel
        binding.lifecycleOwner = this@SavedFragment

      /*  binding.goHome.setOnClickListener {
            findNavController(view).navigate(R.id.action_savedFragment_to_homeFragment)
        }

        binding.goHome.setOnClickListener {
            findNavController(view).navigate(R.id.action_savedFragment_to_profileFragment)
        }
*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_saved, container, false)


        return view
    }

    override fun onDestroy() {
        fragmentSavedBinding = null
        super.onDestroy()

    }
}