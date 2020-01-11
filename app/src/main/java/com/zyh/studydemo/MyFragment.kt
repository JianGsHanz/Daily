package com.zyh.studydemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_my.*

/**
 *Time:2019/11/6
 *Author:zyh
 *Description:
 */
class MyFragment : Fragment(){

    private  var position : Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        position = bundle?.getInt("position")

        Log.e("Log - ${MyFragment.javaClass.name}","position = $position")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv.text = "fragment -- $position"
    }


    companion object{
        fun newInstance(position : Int): MyFragment{
            val bundle = Bundle()
            bundle.putInt("position",position)
            val fragment = MyFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}