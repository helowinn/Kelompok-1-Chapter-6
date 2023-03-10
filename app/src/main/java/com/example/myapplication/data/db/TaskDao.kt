package com.example.myapplication.data.db

import androidx.room.*
import com.example.myapplication.data.model.Task

@Dao
interface TaskDao {

    @Insert
    fun addTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT * FROM task WHERE isFinished = 0")
    fun getAll(): List<Task>

    @Query("DELETE FROM task WHERE id=:uid")
    fun deleteTask(uid:Long)

    @Query("UPDATE task SET isFinished = 1 WHERE id=:uid")
    fun finishTask(uid:Long)

    @Query("SELECT * FROM task WHERE isFinished = 1")
    fun getAllFinish(): List<Task>

}