package com.zyh.databinding.viewmodel

import android.app.Application
import android.text.Editable
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

/**
 *Time:2019/11/7
 *Author:zyh
 *Description: 以注重生命周期的方式管理界面相关的数据（使activity横竖屏切换等情况下不会丢失数据）
 */
class LMainViewModel(application: Application) : AndroidViewModel(application) {
    //创建DataBinding观察属性
    // 设置默认数据
    val name = ObservableField<String>("ZhangYaoHua")
    val age = ObservableField<Int>(18)
    val address = ObservableField<String>("HanDan")
    val img = ObservableField<String>("http://img3.cache.netease.com/photo/0001/2010-02-14/5VFM1R2800AN0001.jpg")

    val userName = ObservableField<String>()
    val passWord = ObservableField<String>()

    //改变属性
    fun changeModel() {
        name.set("ZhangYaoHua${Random.nextInt(100)}")
        age.set(Random.nextInt(100))
        address.set("HanDan ${Random.nextInt(100)}")
    }

    //对应EditText 的 onTextChanged方法
//    fun changeUserName(s: CharSequence?, p1: Int, p2: Int, p3: Int){
//        userName.set(s.toString())
//    }
//
//    fun changePassWord(s: CharSequence?, p1: Int, p2: Int, p3: Int){
//        passWord.set(s.toString())
//    }

    //对应EditText 的 afterTextChanged方法
    fun changeUserName(p0: Editable?) {
        userName.set(p0.toString())
    }

    fun changePassWord(p0: Editable?) {
        passWord.set(p0.toString())
    }

    fun changeImg() {
        img.set("http://b-ssl.duitang.com/uploads/blog/201312/04/20131204184148_hhXUT.jpeg")
    }
}