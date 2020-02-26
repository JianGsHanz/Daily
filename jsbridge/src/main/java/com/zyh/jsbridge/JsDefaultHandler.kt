package com.zyh.jsbridge

import com.github.lzyzsd.jsbridge.CallBackFunction
import com.github.lzyzsd.jsbridge.DefaultHandler

/**
 *Time:2020/2/26
 *Author:zyh
 *Description:
 */
class JsDefaultHandler : DefaultHandler(){
    override fun handler(data: String?, function: CallBackFunction?) {
        e("JS默认回调：$data , $function")
    }
}