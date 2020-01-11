package com.zyh.databinding.viewmodel

import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zyh.databinding.adapter.RvAdapter
import com.zyh.databinding.fragment.HomeFragment
import com.zyh.databinding.model.Person

/**
 *Time:2019/11/13
 *Author:zyh
 *Description:
 */
class HomeViewModel(private val fragment: HomeFragment){

    private lateinit var list: MutableList<Person>
    private lateinit var mAdapter: RvAdapter

    fun init(){
        list = mutableListOf()
        (1..10).forEachIndexed { _, i ->
            list.add(Person("Js$i",0,"",""))
        }
        mAdapter = RvAdapter(fragment,list)
        fragment.homeBinding.rv.apply {
            layoutManager = LinearLayoutManager(fragment.activity,LinearLayoutManager.VERTICAL,false)
            addItemDecoration(DividerItemDecoration(fragment.activity,DividerItemDecoration.VERTICAL))
            adapter = mAdapter
        }
    }

    fun add(){
        val p = Person("Js${list.size+1}",0,"","")
        list.add(0,p)
        mAdapter.notifyDataSetChanged()
    }

    fun remove(){
        if (list.size>0)
            list.removeAt(0)
        mAdapter.notifyDataSetChanged()
    }

    fun onItemClick(person: Person){
        Toast.makeText(fragment.context,"name = ${person.name}",Toast.LENGTH_SHORT).show()
    }
}