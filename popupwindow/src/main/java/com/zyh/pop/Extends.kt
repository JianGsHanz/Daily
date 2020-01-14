package com.zyh.pop

import android.app.Activity
import android.widget.Toast

/**
 *Time:2020/1/14
 *Author:zyh
 *Description:
 */
fun Activity.toast(msg: String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}