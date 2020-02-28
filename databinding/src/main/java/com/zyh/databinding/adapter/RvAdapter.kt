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
class RvAdapter : RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private var mList: MutableList<Person>? = null
    private var fragment: HomeFragment
    private lateinit var viewModel: HomeViewModel

    constructor(fragment: HomeFragment) {
        this.fragment = fragment
    }

    fun setViewModel(viewModel: HomeViewModel){
        this.viewModel = viewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(fragment.context),
                R.layout.item_rv,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList!![position], viewModel) //绑定数据
    }

    class ViewHolder(private var binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person, viewModel: HomeViewModel) {
            binding.person = person
            binding.model = viewModel
        }
    }

    fun setData(mList: MutableList<Person>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    fun add(mList: MutableList<Person>) {
        mList.add(0, Person("Js${mList.size + 1}", 0, ""))
        this.mList = mList
        notifyDataSetChanged()
    }

    fun remove(mList: MutableList<Person>) {
        if (mList.size > 0)
            mList.removeAt(0)
        this.mList = mList
        notifyDataSetChanged()
    }
}