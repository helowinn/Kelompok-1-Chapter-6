package com.example.myapplication.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentLandingPage2Binding

class LandingPage2Fragment : Fragment() {

    private var _binding : FragmentLandingPage2Binding? = null
    private val binding : FragmentLandingPage2Binding
    get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandingPage2Binding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}