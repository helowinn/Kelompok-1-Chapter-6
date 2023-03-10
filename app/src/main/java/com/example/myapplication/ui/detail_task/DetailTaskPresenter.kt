package com.example.myapplication.ui.detail_task

interface DetailTaskPresenter {

    fun deleteTask(id :Long)

    fun updateFinished(id : Long)
}