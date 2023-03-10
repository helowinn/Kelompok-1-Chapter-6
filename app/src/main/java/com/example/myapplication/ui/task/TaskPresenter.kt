package com.example.myapplication.ui.task

interface TaskPresenter {

    fun addTask(id :Long,title: String, description: String,event : String, date: Long, time: Long,)

    fun updateTask(id :Long,title: String, description: String,event : String, date: Long, time: Long,)

}