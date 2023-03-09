package com.example.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.ui.landing.LandingPageActivity

class SplashScreenActivity : AppCompatActivity(), SplashScreenView {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    private var presenter: SplashScreenPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = SplashScreenPresenterImp(this)
        presenter?.checkIsLogin()

    }

    override fun onLogin() {
        val delaySplash = 3000L
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delaySplash)
    }

    override fun unLogin() {
        val delaySplash = 3000L
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
            finish()
        }, delaySplash)
    }
}