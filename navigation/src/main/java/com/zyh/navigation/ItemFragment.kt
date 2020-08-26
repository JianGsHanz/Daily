package com.zyh.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment(), MyItemRecyclerViewAdapter.OnItemClickListener {

    var columnCount = 0
    private val list = mutableListOf<String>()
    private lateinit var  myAdapter : MyItemRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list.apply {
            add("To BlankFragment")
            add("To OtherFragment")
        }
        columnCount = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                myAdapter = MyItemRecyclerViewAdapter(list)
                adapter = myAdapter
            }
        }
        myAdapter.setOnItemClickListener(this)
        return view
    }


    companion object {
        @JvmStatic
        fun newInstance() = ItemFragment()
    }

    override fun onItemClick(position: Int) {
        when(position){
            0 -> findNavController().navigate(R.id.action_itemFragment_to_blankFragment)
            1 -> findNavController().navigate(R.id.action_itemFragment_to_otherFragment)
        }
    }
}