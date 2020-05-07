package com.example.paging.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.paging.dao.Dog
import com.example.paging.dao.DogDao


class DogRepository(private val dogDao: DogDao) {

    suspend fun insert(dog: Dog){
        dogDao.insert(dog)
    }

    suspend fun insertAll(dogs: List<Dog>){
        dogDao.insertAll(dogs)
    }

    fun getAllDog(): DataSource.Factory<Int,Dog> = dogDao.getAllDog()

    fun getAll(): LiveData<MutableList<Dog>> = dogDao.getAll()
}