package com.zyh.viewpage2

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

//    val instance by lazy{
//        this
//    }
//    var instances : MainActivity by NotNullSingleValue()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // by lazy委托需在{}返回实例
//        Log.e("MainActivity","ddd: $instance")
        //by委托需先赋值
//        instances = this
//        Log.e("MainActivity","ddd: $instances")

        val list = mutableListOf<String>()

        (1..8).forEachIndexed { _, i ->
            list.add("tab$i")
        }

//        vp.adapter = ViewPage2Adapter(this,list)
//        vp.orientation = ViewPager2.ORIENTATION_VERTICAL  //垂直滚动
        val adapter = ViewPageFragmentAdapter(this)
        vp.adapter = adapter

        TabLayoutMediator(tabLayout,vp) { tab, position ->
            tab.text = list[position]
        }.run { attach() }

    }


}
