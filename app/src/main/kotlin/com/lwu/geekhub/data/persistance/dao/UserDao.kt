package com.lwu.geekhub.data.persistance.dao

import android.arch.persistence.room.*
import com.lwu.geekhub.data.model.User
import io.reactivex.Maybe

@Dao
interface UserDao {

    @Query("SELECT * FROM Users WHERE id = :id")
    fun getUserById(id: String): Maybe<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM Users")
    fun deleteAllUsers()

    @Query("SELECT * FROM Users WHERE isLoggedIn = 1")
    fun getCurrentUser(): Maybe<User>

    @Query("SELECT * FROM Users")
    fun getAllUsers(): List<User>
}