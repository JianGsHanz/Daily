package com.example.paging.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.paging.dao.Dog
import com.example.paging.dao.MyDataBase
import com.example.paging.repository.DogRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    private val dogRepository: DogRepository
    val listBuilder : LiveData<PagedList<Dog>>
    init {
        val dogDog = MyDataBase.INSTANCE.getDogDao()
        dogRepository = DogRepository(dogDog)

        //配置PageList
        val build = PagedList.Config.Builder()
            .setPageSize(20) //配置分页加载的数量
            .setEnablePlaceholders(false) //配置是否启动占位，如果为true,
            .setInitialLoadSizeHint(20) //初始化加载的数量
            .setPrefetchDistance(3) //距底部还有几条数据时，加载下一页数据
            .build()
        listBuilder = LivePagedListBuilder(getAllDog(), build)
            .build()
    }

    fun insert(dog: Dog){
        viewModelScope.launch {
            dogRepository.insert(dog)
        }
    }

    fun insertAll(dogs: List<Dog>){
        viewModelScope.launch {
            dogRepository.insertAll(dogs)
        }
    }

    private fun getAllDog(): DataSource.Factory<Int,Dog> = dogRepository.getAllDog()

    fun getAll(): LiveData<MutableList<Dog>> = dogRepository.getAll()
 }