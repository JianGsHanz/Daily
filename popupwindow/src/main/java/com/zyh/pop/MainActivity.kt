package com.zyh.pop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private val list = mutableListOf<String>()
    private lateinit var rvPopAdapter: RvPopAdapter
    private lateinit var pop: CommonPopup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..9){
            list.add("item $i")
        }
        initView()
        initListener()
    }

    private fun initView() {
        //初始化pop
        pop = CommonPopup.Builder(this)
            .setView(R.layout.pop_view)
            .setWidthAndHeight(
                ScreenUtil.getScreenWidth(this),
                ScreenUtil.getScreenHeight(this) / 2
            )
            .setOutsideTouchable(true)
            .create()
        //初始化rv
       val rv = pop.getController().mPopupView!!.findViewById<RecyclerView>(R.id.rv)
        rvPopAdapter = RvPopAdapter(this,list)
        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL))
            adapter = rvPopAdapter
        }
    }

    private fun initListener() {
        rvPopAdapter.setOnItemClickListener(object : RvPopAdapter.OnItemClickListener {
            override fun onItemClick(p: Int) {
                toast(list[p])
            }
        })
    }

    fun onClick(view: View) {
        pop.showBottom(bt_show)
        //背景变暗需show后设置
        pop.mPopupController.setBackGroundLevel(0.6f)
        rvPopAdapter.setData(list)
    }

}
