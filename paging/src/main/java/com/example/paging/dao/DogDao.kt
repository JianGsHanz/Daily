package com.example.paging.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * 数据访问对象,实现具体的增删改查
 */
@Dao
interface DogDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)//有冲突是直接替换
    suspend fun insert(dog: Dog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogs: List<Dog>)

    @Query("select * from dog")
    fun getAllDog(): DataSource.Factory<Int, Dog>

    @Query("select * from dog")
    fun getAll():LiveData<MutableList<Dog>>
}