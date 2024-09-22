package com.example.moviebox.ui.account.userRepository

import com.example.moviebox.room.entityUser.EntityUserTable
import com.example.moviebox.room.userDao.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao){

    fun checkUsernameExists(username: String): Flow<Boolean> = flow {
        emit(userDao.existsByUsername(username))
    }
    suspend fun checkEmailExists(email: String): Boolean = userDao.existsByEmail(email)

    suspend fun checkMobileNumberExists(mobileNumber: Long): Boolean =
        userDao.existsByMobileNumber(mobileNumber.toString())

    suspend fun insertUser(user: EntityUserTable) = userDao.insertUser(user)
}