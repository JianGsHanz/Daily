package com.example.paging.ui

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.base.BaseVmActivity
import com.example.paging.R
import com.example.paging.adapter.MyAdapter3
import com.example.paging.viewmodel.Main3ViewModel
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : BaseVmActivity<Main3ViewModel>() {

    lateinit var myAdapter : MyAdapter3
    override fun initView() {
        myAdapter = MyAdapter3()
        val linearLayout = LinearLayoutManager(this)
        rv.apply {
            layoutManager = linearLayout
            addItemDecoration(
                DividerItemDecoration(this@Main3Activity,
                    DividerItemDecoration.VERTICAL)
            )
            adapter = myAdapter
        }

    }

    override fun initData() {
//        mViewModel.test()
        mViewModel.getAll().observe(this, Observer { myAdapter.submitList(it) })

    }

    override fun layoutRes(): Int = R.layout.activity_main3
    override fun viewModelClass() = Main3ViewModel::class.java
}
