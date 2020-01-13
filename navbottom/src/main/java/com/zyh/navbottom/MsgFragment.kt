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
class MsgFragment : Fragment(){

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
        return LayoutInflater.from(activity).inflate(R.layout.fragment_home,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv.text = textStr
    }


    companion object{
        fun newInstance(textStr: String): MsgFragment{
            val bundle = Bundle()
            val fragment = MsgFragment()
            bundle.putString("textStr",textStr)
            fragment.arguments = bundle
            return fragment
        }
    }
}