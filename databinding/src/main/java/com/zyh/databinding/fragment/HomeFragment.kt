package com.zyh.databinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zyh.databinding.R
import com.zyh.databinding.adapter.RvAdapter
import com.zyh.databinding.databinding.FragmentHomeBinding
import com.zyh.databinding.model.Person
import com.zyh.databinding.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *Time:2019/11/12
 *Author:zyh
 *Description:
 */
class HomeFragment :Fragment(){

    private lateinit var homeBinding: FragmentHomeBinding
    private val rvAdapter by lazy {
        RvAdapter(this)
    }
    private val list :MutableList<Person> by lazy {
        mutableListOf<Person>().apply {
            (1..10).forEachIndexed { _, i ->
                add(Person("Js$i",0,""))
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ) as FragmentHomeBinding
        val amvf = ViewModelProvider.AndroidViewModelFactory(activity!!.application)
        val viewModelProvider = ViewModelProvider(activity!!, amvf)
        val viewModel = viewModelProvider.get(HomeViewModel::class.java)
        rvAdapter.setViewModel(viewModel)
        homeBinding.rv.adapter = rvAdapter
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_load.setOnClickListener{
            rvAdapter.setData(list)
        }

        bt_add.setOnClickListener {
            rvAdapter.add(list)
        }

        bt_remove.setOnClickListener {
            rvAdapter.remove(list)
        }
    }
}