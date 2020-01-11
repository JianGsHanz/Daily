package com.zyh.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zyh.databinding.R
import com.zyh.databinding.databinding.ItemRvBinding
import com.zyh.databinding.fragment.HomeFragment
import com.zyh.databinding.model.Person
import com.zyh.databinding.viewmodel.HomeViewModel

/**
 *Time:2019/11/13
 *Author:zyh
 *Description: RV Adapter
 */
class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder>{

    private var mList: MutableList<Person>
    private var fragment:HomeFragment
    constructor(fragment:HomeFragment,mList: MutableList<Person>){
        this.mList = mList
        this.fragment = fragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(fragment.context),R.layout.item_rv,parent,false))

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position],fragment) //绑定数据
    }

    class ViewHolder(private var binding:ItemRvBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(
            person: Person,
            fragment: HomeFragment  //绑定数据
        ){
            binding.person = person
            binding.model = HomeViewModel(fragment)
        }
    }
}