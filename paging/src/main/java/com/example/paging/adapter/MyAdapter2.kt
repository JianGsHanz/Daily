package com.example.paging.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R
import kotlinx.android.synthetic.main.item_rv.view.*

class MyAdapter2 : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var list: ArrayList<String> = ArrayList()

    companion object{
        private val VIEW_HEAD = 0
        private val VIEW_CONTENT = 1
        private val VIEW_FOOTER = 2

    }
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view)
    class ViewHolder1(view:View) : RecyclerView.ViewHolder(view)
    class ViewHolder2(view:View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_CONTENT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
                ViewHolder(view)
            }
            VIEW_HEAD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_head, parent, false)
                ViewHolder2(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_footer, parent, false)
                ViewHolder1(view)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder){
            holder.itemView.tv.text = list[position]
        }else{

        }

    }
    override fun onAttachedToRecyclerView(@NonNull recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager: GridLayoutManager? = recyclerView.layoutManager as GridLayoutManager?
        manager?.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == 0 || position >= list.size) return 2
                return 1
            }

        }
    }
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            list.size -> {
                VIEW_FOOTER
            }
            0 -> {
                VIEW_HEAD
            }
            else -> {
                VIEW_CONTENT
            }
        }
    }

    override fun getItemCount(): Int = list.size + 1


    fun setData(list: ArrayList<String>){
        this.list.addAll(list)
        Log.e("Adapter","list = $list ${list.size}, ${this.list} ${this.list.size}")
        notifyDataSetChanged()
    }
}