package com.zyh.room

import androidx.lifecycle.LiveData

/**
 *Time:2020/3/2
 *Author:zyh
 *Description:model
 */
class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User){
        userDao.insert(user)
    }

    suspend fun insertAll(vararg user: User){
        userDao.insertAll(*user)
    }

    suspend fun deleteAll(){
        userDao.deleteAll()
    }

    suspend fun deleteById(id:Int){
        userDao.deleteById(id)
    }

    suspend fun update(oldId:Int = 0,userName: String = "",userSex: String = ""){
        userDao.update(oldId,userName,userSex)
    }

    fun queryByField(id: Int,userAny: String) = userDao.queryByField(id,userAny)

    fun queryAll():LiveData<MutableList<User>> = userDao.queryAll()
}