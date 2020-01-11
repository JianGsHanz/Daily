package com.zyh.databinding

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.zyh.databinding.databinding.ActivityMainBinding
import com.zyh.databinding.model.Person
import com.zyh.databinding.viewmodel.LMainViewModel
import java.io.File

@BindingAdapter("android:src")
fun loadImg(iv:ImageView,url:String){   //动态改变ImageView，需通过注解@BindingAdapter自定义属性,
    Log.e("loadImg()--->", url)    //且必须是顶层函数或静态方法,kotlin 必须加 apply plugin: 'kotlin-kapt'
    Glide.with(iv.context).load(url).into(iv)
}
//object Adapter{
//    @JvmStatic
//    @BindingAdapter("android:src")
//    fun loadImg(iv:ImageView,url:String){
//        Glide.with(iv.context).load(url).into(iv)
//    }
//}
class LMainActivity : AppCompatActivity() {

    private lateinit var viewModel: LMainViewModel
    private lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        person = Person("ZhangYaoHua",23,"HanDan","http://img3.cache.netease.com/photo/0001/2010-02-14/5VFM1R2800AN0001.jpg")
        viewModel = LMainViewModel(person,this)
        //为布局variable赋值
        binding.personInfo = person
        binding.model = viewModel
    }
}