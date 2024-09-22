package com.example.moviebox.di

import android.content.Context
import androidx.room.Room
import com.example.moviebox.room.userDao.UserDao
import com.example.moviebox.room.userDatabase.UserDatabase
import com.example.moviebox.ui.account.userRepository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "user_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()  // Provide UserDao instance from UserDatabase
    }

}




