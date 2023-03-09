package com.example.myapplication.ui.splash

import com.example.myapplication.data.local.SharedPref

class SplashScreenPresenterImp(private val view : SplashScreenView) : SplashScreenPresenter {

    override fun checkIsLogin(){
        if (SharedPref.isLogin == true)
            view.onLogin()
        else
            view.unLogin()
    }
}