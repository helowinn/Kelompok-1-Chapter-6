package com.example.myapplication.ui.dashboard

import com.example.myapplication.data.model.Task

interface DashboardView {

    fun onSuccess(database: List<Task>)

    fun onError(msg : String)
}