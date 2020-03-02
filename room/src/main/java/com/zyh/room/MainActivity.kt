package com.zyh.room

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private val sb = StringBuilder()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val androidViewModelFactory = ViewModelProvider.AndroidViewModelFactory(application)
        val viewModelProvider = ViewModelProvider(this, androidViewModelFactory)
        viewModel = viewModelProvider.get(UserViewModel::class.java)


        CoroutineScope(Dispatchers.Main).launch {
            viewModel.queryAll().observe(this@MainActivity, Observer { it ->
                sb.setLength(0)
                it.forEach {
                    sb.append("编号:${it.userId} 姓名:${it.userName} 性别:${it.userSex} 年龄:${it.userAge} 长相:${it.userImage}").append("\n")
                }
                tvs.text = sb.toString()
            })
        }
        initListener()
    }

    private fun initListener() {
        bt_insert.setOnClickListener {
//            viewModel.insert(User("无糖","男"))
            viewModel.insertAll(User("张三", "男",18,"帅"), User("李四", "女",20,"丑"))
        }
        bt_update.setOnClickListener {
             viewModel.update(153, "露露", "女")
        }
        bt_clear.setOnClickListener {
//             viewModel.deleteById(8)
            viewModel.deleteAll()
        }
        bt_query.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch{
                viewModel.queryByField(153,"").observe(this@MainActivity, Observer { it ->
                    sb.setLength(0)
                    it.forEach {
                        sb.append("编号:${it.userId} 姓名:${it.userName} 性别:${it.userSex} 年龄:${it.userAge} 长相:${it.userImage}").append("\n")
                    }
                    tvs.text = sb.toString()
                })
            }
        }
    }
}
