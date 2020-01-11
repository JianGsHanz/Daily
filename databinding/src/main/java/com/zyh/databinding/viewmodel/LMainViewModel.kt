package com.zyh.databinding.viewmodel

import android.content.Intent
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import com.zyh.databinding.LMainActivity
import com.zyh.databinding.SecondActivity
import com.zyh.databinding.model.Person
import kotlin.random.Random

/**
 *Time:2019/11/7
 *Author:zyh
 *Description:
 */
class LMainViewModel(person: Person, private var context: LMainActivity) {
    //观察属性
    val name = ObservableField(person.name)
    val age = ObservableField(person.age)
    val address = ObservableField(person.address)
    val img = ObservableField(person.img)

    val userName = ObservableField("")
    val passWord = ObservableField("")

    //改变属性
    fun changeModel(){
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
    fun changeUserName(p0: Editable?){
        userName.set(p0.toString())
    }

    fun changePassWord(p0: Editable?){
        passWord.set(p0.toString())
    }

    fun login(){
        Log.e("login()","UserName = ${userName.get()},PassWord = ${passWord.get()}")
        if (userName.get() == "abs"&&passWord.get() == "123"){
            Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context,SecondActivity::class.java))
        }else{
            Toast.makeText(context,"输入：UserName = ${userName.get()},PassWord = ${passWord.get()}",Toast.LENGTH_SHORT).show()
        }
    }

    fun changeImg(){
        img.set("http://b-ssl.duitang.com/uploads/blog/201312/04/20131204184148_hhXUT.jpeg")
    }
}