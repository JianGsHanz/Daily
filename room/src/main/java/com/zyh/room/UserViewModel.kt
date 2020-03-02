package com.zyh.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *Time:2020/3/2
 *Author:zyh
 *Description:ViewModel
 */
class UserViewModel(application: Application) : AndroidViewModel(application){

    private val userRepository: UserRepository
    init {
        val userDao = RDataBase.INSTANCE.getUserDao()
        userRepository = UserRepository(userDao)
    }

    fun insert(user: User){
        CoroutineScope(Dispatchers.Default).launch {
            userRepository.insert(user)
        }
    }

    fun insertAll(vararg user: User){
        CoroutineScope(Dispatchers.Default).launch {
            userRepository.insertAll(*user)
        }
    }

    fun deleteAll(){
        CoroutineScope(Dispatchers.Default).launch {
            userRepository.deleteAll()
        }
    }

    fun deleteById(id:Int){
        CoroutineScope(Dispatchers.Default).launch {
            userRepository.deleteById(id)
        }
    }

    fun update(oldId:Int = 0,userName: String = "",userSex: String = ""){
        CoroutineScope(Dispatchers.Default).launch {
            userRepository.update(oldId,userName,userSex)
        }
    }

    fun queryByField(id: Int,userAny: String) = userRepository.queryByField(id,userAny)

    fun queryAll(): LiveData<MutableList<User>> = userRepository.queryAll()
}