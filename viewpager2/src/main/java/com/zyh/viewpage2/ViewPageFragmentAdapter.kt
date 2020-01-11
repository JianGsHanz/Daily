package com.zyh.viewpage2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *Time:2019/11/6
 *Author:zyh
 *Description:
 */
class ViewPageFragmentAdapter : FragmentStateAdapter {

    private val lists = mutableListOf<MyFragment>()

    constructor(fm: FragmentActivity):super(fm){
        (1..8).forEachIndexed { _, i ->
            lists.add(MyFragment.newInstance(i))
        }
    }

    override fun getItemCount(): Int = lists.size

    override fun createFragment(position: Int): Fragment = lists[position]

}