package com.example.myapplication.ui.history

import com.example.myapplication.data.model.Task

interface HistoryView {

    fun onSuccess(database: List<Task>)
}