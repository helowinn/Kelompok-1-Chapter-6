package com.example.myapplication.ui.profile

import com.example.myapplication.data.model.Task
import com.example.myapplication.data.model.User

interface ProfileView {

    fun onSuccessCompleted(database: List<Task>)
    fun onSuccessTask(database: List<Task>)

    fun onSuccess()
    fun onSuccessUser(user : User)
}