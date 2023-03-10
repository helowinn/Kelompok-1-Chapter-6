package com.example.myapplication.ui.dashboard

import com.example.myapplication.data.db.MyDatabase
import com.example.myapplication.utils.Apps.Companion.context
import com.example.myapplication.utils.Apps.Companion.mDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardPresenterImp (private val view : DashboardView) : DashboardPresenter {
    override fun getData() {
        mDB = context.get()?.let {MyDatabase.getInstance(it)}

        CoroutineScope(Dispatchers.IO).launch {
            val database = mDB!!.taskDao().getAll()
            withContext(Dispatchers.Main){
                view.onSuccess(database)
            }
        }
    }

}