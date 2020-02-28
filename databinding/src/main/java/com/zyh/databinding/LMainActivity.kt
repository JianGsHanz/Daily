package com.zyh.databinding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.zyh.databinding.databinding.ActivityMainBinding
import com.zyh.databinding.viewmodel.LMainViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val avmf = ViewModelProvider.AndroidViewModelFactory(application)
        val viewModelProvider = ViewModelProvider(this, avmf)
        viewModel = viewModelProvider.get(LMainViewModel::class.java)
        //设置ViewModel
        binding.model = viewModel
    }

    fun onLogin(view: View) {
        Log.e("login()","UserName = ${viewModel.userName},PassWord = ${viewModel.passWord}")
        if (viewModel.userName.get() == "abs"&&viewModel.passWord.get() == "123"){
            Toast.makeText(this,"登录成功", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,SecondActivity::class.java))
        }else{
            Toast.makeText(this,"输入：UserName = ${viewModel.userName},PassWord = ${viewModel.passWord}",
                Toast.LENGTH_SHORT).show()
        }
    }
}