package com.example.myapplication.ui.task

interface TaskView {

    fun onSuccess()

    fun onSuccessUpdate()

    fun onError(msg : String)
}