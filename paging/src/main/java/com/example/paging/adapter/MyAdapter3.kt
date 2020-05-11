package com.example.paging.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R
import com.example.paging.bean.GithubAccount
import com.example.paging.dao.Dog
import kotlinx.android.synthetic.main.item_rv.view.*

class MyAdapter3 : PagedListAdapter<GithubAccount,MyAdapter3.ViewHolder>(differ){


    companion object{
        private val differ = object :  DiffUtil.ItemCallback<GithubAccount>(){
            override fun areItemsTheSame(oldItem: GithubAccount, newItem: GithubAccount): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GithubAccount, newItem: GithubAccount): Boolean = oldItem == newItem
        }
    }
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.itemView.tv.text = "${it.id} - ${it.avatar_url} - ${it.login}"
        }
    }
}