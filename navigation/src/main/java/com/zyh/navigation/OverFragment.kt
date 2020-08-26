package com.zyh.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_over.*

class OverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        e("OverFragment->onCreateView")
        return inflater.inflate(R.layout.fragment_over, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_over.setOnClickListener {
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = OverFragment()
    }
}