package com.zyh.viewpage2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewpage_item.view.*

/**
 *Time:2019/11/6
 *Author:zyh
 *Description:
 */
class ViewPage2Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var context: Context
    private var list: MutableList<String>
    private var colorList = mutableListOf(Color.BLUE,Color.CYAN,Color.GREEN)

    constructor(context: Context,list: MutableList<String>){
        this.context = context
        this.list = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return ViewHolder(LayoutInflater.from(context).inflate(R.layout.viewpage_item,parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv.run {
            text = list[position]
            setTextColor(colorList[position])
        }
    }

    class ViewHolder(view :View):RecyclerView.ViewHolder(view)
}