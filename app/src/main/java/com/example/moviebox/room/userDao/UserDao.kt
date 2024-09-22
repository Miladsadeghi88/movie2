package com.example.moviebox.room.userDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviebox.room.entityUser.EntityUserTable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: EntityUserTable)

    @Query("SELECT * FROM user_table WHERE username = :username  LIMIT 1")
    suspend fun getUserByUsername(username: String): EntityUserTable?

    @Query("SELECT * FROM user_table WHERE email = :email OR phone_number = :phoneNumber LIMIT 1")
    suspend fun getUserByEmailOrPhone(email: String?, phoneNumber: Long?): EntityUserTable?

    @Query("UPDATE USER_TABLE SET password = :newPassword WHERE username = :username")
    suspend fun updatePassword(username: String, newPassword: String)

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE username = :username)")
    suspend fun existsByUsername(username: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE email = :email)")
    suspend fun existsByEmail(email: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE phone_number = :mobileNumber)")
    suspend fun existsByMobileNumber(mobileNumber: String): Boolean
}
