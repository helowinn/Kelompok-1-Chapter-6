package com.example.myapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentProfileBinding
import com.example.myapplication.ui.calendar.CalendarFragment
import com.example.myapplication.ui.dashboard.DashboardFragment
import com.example.myapplication.ui.history.HistoryFragment
import com.example.myapplication.ui.landing.LandingPage1Fragment
import com.example.myapplication.ui.profile.ProfileFragment
import com.example.myapplication.ui.task.TaskActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = DashboardFragment()
        addFragment(fragment)

        binding.fab.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java)
            startActivity(intent)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> {
                val fragment = DashboardFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                val fragment = HistoryFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.date -> {
                val fragment = CalendarFragment()
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }R.id.profile -> {
            val fragment = ProfileFragment()
            addFragment(fragment)
            return@OnNavigationItemSelectedListener true
        }
        }
        false
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(com.google.android.material.R.anim.design_bottom_sheet_slide_in,
                com.google.android.material.R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.fl_root,fragment,fragment.javaClass.simpleName)
            .commit()
    }
}