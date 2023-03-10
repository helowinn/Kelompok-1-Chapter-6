package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var title:String,
    var description:String,
    var event : String,
    var date:Long,
    var time:Long,
    var isFinished : Int = 0
)