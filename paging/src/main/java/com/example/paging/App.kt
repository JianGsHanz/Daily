package com.example.paging

import android.app.Application
import kotlin.properties.Delegates

/**
 *Time:2020/3/2
 *Author:zyh
 *Description:
 */
class App :Application(){

    companion object{
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}