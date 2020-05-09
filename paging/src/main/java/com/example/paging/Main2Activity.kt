package com.example.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.paging.adapter.MyAdapter
import com.example.paging.adapter.MyAdapter2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

/**
 * 自定义Recyclerview加载更多
 */
class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val myAdapter = MyAdapter2()
        val linearLayout = GridLayoutManager(this,2, GridLayoutManager.HORIZONTAL,false)
        rv2.apply {
            layoutManager = linearLayout

            adapter = myAdapter

            addOnScrollListener(object : RecyclerOnScrollListener(){
                override fun onLoadNextPage(view: View?) {
                    super.onLoadNextPage(view)
                    Log.e("onLoadNextPage","模拟网络")
                    Handler().postDelayed({
                        myAdapter.setData(initData())
                    },2000)
                }
            })
        }
        myAdapter.setData(initData())
    }
    fun initData(): ArrayList<String>{
        val list = ArrayList<String>()
        for (s in 0..10) {
            list.add("zhang $s")
        }
        return list
    }
}
