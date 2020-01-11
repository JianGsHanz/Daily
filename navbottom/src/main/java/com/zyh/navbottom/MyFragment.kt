package com.zyh.navbottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_my.*

/**
 *Time:2020/1/10
 *Author:zyh
 *Description:
 */
class MyFragment : Fragment(){

    private var textStr : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textStr = arguments?.getString("textStr")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_my,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv.text = textStr
    }


    companion object{
        fun instance(textStr: String): MyFragment{
            val bundle = Bundle()
            val fragment = MyFragment()
            bundle.putString("textStr",textStr)
            fragment.arguments = bundle
            return fragment
        }
    }
}