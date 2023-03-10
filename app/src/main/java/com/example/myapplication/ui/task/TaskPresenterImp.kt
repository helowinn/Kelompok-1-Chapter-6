package com.example.myapplication.ui.task

import com.example.myapplication.data.db.MyDatabase
import com.example.myapplication.data.model.Task
import com.example.myapplication.utils.Apps.Companion.context
import com.example.myapplication.utils.Apps.Companion.mDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskPresenterImp (private val view : TaskView) : TaskPresenter {

    override fun addTask(id: Long, title: String, description: String,event : String, date: Long, time: Long) {
        mDB = context.get()?.let { MyDatabase.getInstance(it) }

        CoroutineScope(Dispatchers.IO).launch {
            mDB?.taskDao()?.addTask(Task(0,title,description,event,date,time))
            withContext(Dispatchers.Main){
                view.onSuccess()
            }
        }
    }

    override fun updateTask(id: Long, title: String, description: String,event : String, date: Long, time: Long) {
        mDB = context.get()?.let { MyDatabase.getInstance(it) }

        CoroutineScope(Dispatchers.IO).launch {
            mDB?.taskDao()?.updateTask(Task(id,title,description,event,date,time))
            withContext(Dispatchers.Main){
                view.onSuccessUpdate()
            }
        }
    }

}