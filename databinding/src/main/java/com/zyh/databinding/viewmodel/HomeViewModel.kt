package com.zyh.databinding.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
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
class HomeViewModel(application: Application): AndroidViewModel(application) {

    fun onItemClick(person: Person){
        Toast.makeText(getApplication(),"name = ${person.name}",Toast.LENGTH_SHORT).show()
    }
}