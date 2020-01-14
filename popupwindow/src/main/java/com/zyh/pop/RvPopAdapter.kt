package com.zyh.pop

/**
 *Time:2020/1/14
 *Author:zyh
 *Description:
 */
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rv_item.view.*

class RvPopAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var context: Context
    private var list: MutableList<String>
    private var listener:OnItemClickListener? = null

    constructor(context: Context,list: MutableList<String>) {
        this.context = context
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))

    override fun getItemCount(): Int =  if (list.size != 0){ list.size }else 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p: Int) {
        holder.itemView.tv.text = list[p]
        holder.itemView.setOnClickListener {
            listener?.onItemClick(p)
        }
    }

    fun setData(list: MutableList<String>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(p:Int)
    }
    fun setOnItemClickListener(listener:OnItemClickListener){
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}