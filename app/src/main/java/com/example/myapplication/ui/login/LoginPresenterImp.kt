package com.example.myapplication.ui.login

import com.example.myapplication.data.db.MyDatabase
import com.example.myapplication.data.local.SharedPref
import com.example.myapplication.utils.Apps.Companion.context
import com.example.myapplication.utils.Apps.Companion.mDB
import kotlinx.coroutines.*

class LoginPresenterImp(private val  view: LoginView) : LoginPresenter {

    override fun login(username: String, password: String) {
        mDB = context.get()?.let { MyDatabase.getInstance(it) }

        GlobalScope.launch(Dispatchers.IO) {
            val user = mDB?.userDao()?.getUserByUsername(username)

            launch(Dispatchers.Main) {
                if (user == null) {
                    view.onError("Username belum terdaftar")
                } else {
                    val passwordDB = user.password
                    if (password != passwordDB)
                        view.onError("Password salah")
                    else{
                        SharedPref.id = user.id
                        SharedPref.username = user.username
                        SharedPref.isLogin = true
                        view.onSuccess()
                    }
                }
            }
        }

    }
}