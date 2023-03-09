package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmate.data.model.User

@Database(entities = [User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{
        private var INSTANCE : MyDatabase? =null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null){
                synchronized(MyDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MyDatabase::class.java, "myDatabase").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}