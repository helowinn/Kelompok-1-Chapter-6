package com.example.myapplication.ui.history

import com.example.myapplication.data.db.MyDatabase
import com.example.myapplication.utils.Apps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryPresenterImp (private val view : HistoryView) : HistoryPresenter {
    override fun getDataFinished() {
        Apps.mDB = Apps.context.get()?.let { MyDatabase.getInstance(it)}

        CoroutineScope(Dispatchers.IO).launch {
            val database = Apps.mDB!!.taskDao().getAllFinish()
            withContext(Dispatchers.Main){
                view.onSuccess(database)
            }
        }
    }
}