package com.zyh.navbottom

import android.app.Activity
import android.util.Log
import android.widget.Toast

/**
 *Time:2020/1/10
 *Author:zyh
 *Description: 扩展
 */
fun Activity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.e(msg: String) {
    Log.e(this::class.java.simpleName,msg)
}