package com.example.myapplication.utils

import android.app.Application
import android.content.Context
import com.example.myapplication.data.db.MyDatabase
import java.lang.ref.WeakReference

class Apps : Application() {

    companion object{
        lateinit var context : WeakReference<Context>
        var mDB : MyDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        context =WeakReference(applicationContext)
    }
}