package com.example.myapplication.data.db

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.example.taskmate.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM User WHERE username = :username OR email = :username")
    fun getUserByUsername(username:String): User
}