package com.zyh.dagger2.di.commponent

import com.zyh.dagger2.MainActivity
import com.zyh.dagger2.SecondActivity
import com.zyh.dagger2.di.module.ActivityModule
import com.zyh.dagger2.di.scope.PerActivity
import dagger.Component

/**
 *Time:2019/12/5
 *Author:zyh
 *Description:
 */
@PerActivity
@Component(dependencies = [AppCommponent::class],modules = [ActivityModule::class])
interface ActivityCommponent{
    fun inject(activity: MainActivity)
    fun inject(activity: SecondActivity)
}