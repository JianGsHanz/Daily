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
import kotlinx.android.synthetic.main.item_rv.view.*

class MyAdapter3 : PagedListAdapter<GithubAccount,RecyclerView.ViewHolder>(differ){


    companion object{
        private const val VIEW_CONTENT = 1
        private const val VIEW_FOOTER = 2
        private val differ = object :  DiffUtil.ItemCallback<GithubAccount>(){
            override fun areItemsTheSame(oldItem: GithubAccount, newItem: GithubAccount): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GithubAccount, newItem: GithubAccount): Boolean = oldItem == newItem
        }
    }
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view)
    class ViewHolder1(view:View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == VIEW_CONTENT){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
            ViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_footer_vertical, parent, false)
            ViewHolder1(view)
        }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position <= currentList!!.size - 1){
            getItem(position)?.let {
                holder.itemView.tv.text = "${it.id} - ${it.avatar_url} - ${it.login}"
            }
        }
    }

    override fun getItemViewType(position: Int): Int = if (position == currentList?.size){
        VIEW_FOOTER
    }else{
        VIEW_CONTENT
    }

    override fun getItemCount(): Int = currentList?.size?.plus(1) ?: 0
}