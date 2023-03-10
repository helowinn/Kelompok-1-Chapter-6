package com.example.myapplication.ui.profile

import com.example.myapplication.data.model.User

interface ProfilePresenter {

    fun logout()
    fun getData()

    fun getDataFinished()

    fun getDataUser(user: String)
    fun updateUser(id : Int,username: String, password: String, email: String)
}