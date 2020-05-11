package com.example.paging.viewmodel

import com.example.common.base.BaseViewModel
import com.example.paging.repository.Main3Repository

class Main3ViewModel :BaseViewModel(){

    private val mMain3Repository by lazy { Main3Repository() }

    fun test(){
        launch(
            block = {
                val test = mMain3Repository.test()
                println("block == $test")
            },
            error = {
                println("error == ${it.message}")
            }
        )
    }

    fun getAll() = mMain3Repository.getAll()
}