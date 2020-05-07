package com.example.paging.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog")
data class Dog(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name: String,
    val sex: String
)