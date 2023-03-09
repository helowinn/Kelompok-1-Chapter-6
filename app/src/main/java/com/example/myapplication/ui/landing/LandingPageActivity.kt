package com.example.myapplication.ui.landing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLandingPageBinding
import com.example.myapplication.ui.landing.adapter.LandingPageAdapter

class LandingPageActivity : AppCompatActivity(){

    private val binding : ActivityLandingPageBinding by lazy {
        ActivityLandingPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val landingPage1 = LandingPage1Fragment()
        val landingPage2 = LandingPage2Fragment()
        val landingPage3 = LandingPage3Fragment()

        val landingPageAdapter = LandingPageAdapter(
            fragmentManager = supportFragmentManager,
            landingPage1,landingPage2,landingPage3)

        binding.viewPager.adapter = landingPageAdapter
        binding.viewIndicator.setViewPager(binding.viewPager)
    }
}