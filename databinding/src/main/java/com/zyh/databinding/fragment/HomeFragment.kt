package com.zyh.databinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zyh.databinding.R
import com.zyh.databinding.databinding.FragmentHomeBinding
import com.zyh.databinding.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *Time:2019/11/12
 *Author:zyh
 *Description:
 */
class HomeFragment :Fragment(){

    lateinit var homeBinding: FragmentHomeBinding
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
        homeBinding.model = HomeViewModel(this)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_load.setOnClickListener{
            homeBinding.model!!.init()
        }

        bt_add.setOnClickListener {
            homeBinding.model!!.add()
        }

        bt_remove.setOnClickListener {
            homeBinding.model!!.remove()
        }
    }
}