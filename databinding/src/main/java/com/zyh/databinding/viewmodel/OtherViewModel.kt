package com.zyh.databinding.viewmodel

import android.Manifest
import android.annotation.SuppressLint
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zyh.databinding.fragment.OtherFragment
import io.reactivex.functions.Consumer


/**
 *Time:2019/11/12
 *Author:zyh
 *Description:
 */
class OtherViewModel(var fragment: OtherFragment)  {

    @SuppressLint("CheckResult")
    fun clickPermission(){
        RxPermissions(fragment)
            .requestEach(Manifest.permission.READ_CONTACTS)
            .subscribe(Consumer {

            })
    }

}