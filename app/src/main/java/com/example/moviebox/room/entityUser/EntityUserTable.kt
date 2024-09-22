package com.example.moviebox.room.entityUser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_table",
    indices = [Index(value = ["Email"], unique = true), Index(
        value = ["Mobile_Number"],
        unique = true
    )]
)
data class EntityUserTable(
    @PrimaryKey
    @ColumnInfo(name = "username")
    val username: String, // Username must be unique by default

    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "Email")
    val email: String,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: Long
)

