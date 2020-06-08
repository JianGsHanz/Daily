package com.example.paging.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.common.ContextHolder

@Database(entities = [Dog::class],version = 1)
abstract class MyDataBase: RoomDatabase() {

    companion object{
        val INSTANCE: MyDataBase by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
            Room.databaseBuilder(
                ContextHolder.mAppContext,
                MyDataBase::class.java,"Data.db")
                .build()
        }
    }

    abstract fun getDogDao(): DogDao
}