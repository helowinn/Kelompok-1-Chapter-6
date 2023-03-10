package com.example.myapplication.ui.profile

import com.example.myapplication.data.db.MyDatabase
import com.example.myapplication.data.local.SharedPref
import com.example.myapplication.data.model.User
import com.example.myapplication.utils.Apps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfilePresenterImp(private val view : ProfileView) : ProfilePresenter {

    override fun logout() {
        SharedPref.isLogin=false
    }

    override fun getData() {
        Apps.mDB = Apps.context.get()?.let { MyDatabase.getInstance(it)}

        CoroutineScope(Dispatchers.IO).launch {
            val database = Apps.mDB!!.taskDao().getAll()
            withContext(Dispatchers.Main){
                view.onSuccessTask(database)
            }
        }
    }

    override fun getDataFinished() {
        Apps.mDB = Apps.context.get()?.let { MyDatabase.getInstance(it)}

        CoroutineScope(Dispatchers.IO).launch {
            val database = Apps.mDB!!.taskDao().getAllFinish()
            withContext(Dispatchers.Main){
                view.onSuccessCompleted(database)
            }
        }
    }

    override fun updateUser(id : Int ,username: String, password: String, email: String) {
        Apps.mDB = Apps.context.get()?.let { MyDatabase.getInstance(it)}

        val user = User(id, username, password, email)
        CoroutineScope(Dispatchers.IO).launch {
           Apps.mDB!!.userDao().updateUser(user)
            withContext(Dispatchers.Main){
                view.onSuccess()
            }
        }
    }

    override fun getDataUser(user: String) {
        Apps.mDB = Apps.context.get()?.let { MyDatabase.getInstance(it)}

        CoroutineScope(Dispatchers.IO).launch {
            val database = Apps.mDB!!.userDao().getUserByUsername(user)
            withContext(Dispatchers.Main){
                view.onSuccessUser(database)
            }
        }
    }
}