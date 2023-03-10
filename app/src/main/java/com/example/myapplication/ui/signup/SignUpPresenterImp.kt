package com.example.myapplication.ui.signup

import com.example.myapplication.data.db.MyDatabase
import com.example.myapplication.utils.Apps.Companion.context
import com.example.myapplication.utils.Apps.Companion.mDB
import com.example.myapplication.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpPresenterImp(private val view : SignUpView) : SignUpPresenter {

    override fun signUp(username: String, password: String, email: String) {
        mDB = context.get()?.let { MyDatabase.getInstance(it) }
        val user = User(null, username, password, email)
        CoroutineScope(Dispatchers.IO).launch {
            val checker = mDB?.userDao()?.getUserByUsername(username)
            if(checker == null){
                mDB?.userDao()?.insertUser(user)
            }
            launch(Dispatchers.Main) {
                if (checker == null){
                    view.onSuccess()
                }
                else{
                    view.onError("Username telah digunakan")
                }
            }
        }
    }

}