package com.zyh.dagger2.di.module

import android.content.Context
import com.zyh.dagger2.di.scope.PerApp
import dagger.Module
import dagger.Provides

/**
 *Time:2019/12/5
 *Author:zyh
 *Description:
 */

@Module
class AppModule (private val context: Context){
    @PerApp
    @Provides
    fun provideContext(): Context = context
}