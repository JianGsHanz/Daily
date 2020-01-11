package com.zyh.databinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.zyh.databinding.databinding.FragmentOtherBinding
import com.zyh.databinding.viewmodel.OtherViewModel


/**
 *Time:2019/11/12
 *Author:zyh
 *Description:
 */
class OtherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val otherBinding =
            DataBindingUtil.inflate(
                inflater,
                com.zyh.databinding.R.layout.fragment_other,
                container,
                false
            ) as FragmentOtherBinding
        val otherModel = OtherViewModel(this)
        otherBinding.model = otherModel
        return otherBinding.root
    }
}