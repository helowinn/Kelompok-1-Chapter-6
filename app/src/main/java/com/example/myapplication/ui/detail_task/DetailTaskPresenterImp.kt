package com.example.myapplication.ui.detail_task

import com.example.myapplication.data.db.MyDatabase
import com.example.myapplication.data.model.Task
import com.example.myapplication.utils.Apps.Companion.context
import com.example.myapplication.utils.Apps.Companion.mDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailTaskPresenterImp (private val view : DetailTaskView) : DetailTaskPresenter{
    override fun deleteTask(id: Long) {
        mDB = context.get()?.let { MyDatabase.getInstance(it) }

        CoroutineScope(Dispatchers.IO).launch {
            mDB?.taskDao()?.deleteTask(id)
            withContext(Dispatchers.Main){
                view.onSuccess()
            }
        }
    }

    override fun updateFinished(id : Long) {
        mDB = context.get()?.let {MyDatabase.getInstance(it)}

        CoroutineScope(Dispatchers.IO).launch {
            mDB!!.taskDao().finishTask(id)
            withContext(Dispatchers.Main){
                view.onSuccess()
            }
        }
    }

}