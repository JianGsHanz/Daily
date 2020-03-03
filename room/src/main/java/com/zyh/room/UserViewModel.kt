package com.zyh.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
        viewModelScope.launch(Dispatchers.Default){
            userRepository.insert(user)
        }
    }

    fun insertAll(vararg user: User){
        viewModelScope.launch(Dispatchers.Default){
            userRepository.insertAll(*user)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.Default){
            userRepository.deleteAll()
        }
    }

    fun deleteById(id:Int){
        viewModelScope.launch(Dispatchers.Default){
            userRepository.deleteById(id)
        }
    }

    fun update(oldId:Int = 0,userName: String = "",userSex: String = ""){
        viewModelScope.launch(Dispatchers.Default){
            userRepository.update(oldId,userName,userSex)
        }
    }

    fun queryByField(id: Int,userAny: String) = userRepository.queryByField(id,userAny)

    fun queryAll(): LiveData<MutableList<User>> = userRepository.queryAll()
}