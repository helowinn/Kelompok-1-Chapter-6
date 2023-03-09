package com.example.myapplication.ui.login

interface LoginView {

   fun onSuccess()

   fun onError(msg: String)
}