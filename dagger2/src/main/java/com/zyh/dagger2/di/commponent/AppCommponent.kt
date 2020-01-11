package com.zyh.dagger2.di.commponent

import android.content.Context
import com.zyh.dagger2.di.module.AppModule
import com.zyh.dagger2.di.scope.PerApp
import dagger.Component

/**
 *Time:2019/12/5
 *Author:zyh
 * Description:
 * 如果只是给Application级别添加了作用域，编译是会报错的，因为咱们的ActivityComponent依赖于它，
 * 而ActivityComponent是没有作用域，这个时候会报出错误（(unscoped) cannot depend on scoped components），
 * 很明显，没有scope的component不能依赖于有scope的component。
 */
@PerApp
@Component(modules = [AppModule::class])
interface AppCommponent {
    fun context():Context
}