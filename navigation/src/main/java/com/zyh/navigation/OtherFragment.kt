package com.zyh.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_other.*

class OtherFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        e("OtherFragment->onCreateView")
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_other.setOnClickListener {
            findNavController().navigate(R.id.action_otherFragment_to_overFragment)
        }

    }
    companion object {
        @JvmStatic
        fun newInstance() =
            OtherFragment()
    }
}