package com.zyh.dagger2

import android.app.Application
import com.zyh.dagger2.di.commponent.ActivityCommponent
import com.zyh.dagger2.di.commponent.DaggerActivityCommponent
import com.zyh.dagger2.di.commponent.DaggerAppCommponent
import com.zyh.dagger2.di.module.ActivityModule
import com.zyh.dagger2.di.module.AppModule
import kotlin.properties.Delegates

/**
 *Time:2019/11/28
 *Author:zyh
 *Description:
 */
class MyApp : Application() {

    lateinit var activityCommponent: ActivityCommponent

    companion object{
        var app: MyApp by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        app = this
        initJect()
    }

    private fun initJect() {
        val appCommponent = DaggerAppCommponent.builder().appModule(AppModule(this)).build()
        activityCommponent = DaggerActivityCommponent.builder().appCommponent(appCommponent).activityModule(
            ActivityModule()
        ).build()
    }
}