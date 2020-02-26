package com.zyh.jsbridge

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val jsWebChromeClient: JsWebChromeClient
        get() {
           return JsWebChromeClient(this@MainActivity)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWebView()

        bt_callJs.setOnClickListener {
            web.callHandler("functionInJs","我是Android发给JS的消息") { data: String? ->
                e("android调js成功回调 $data")
            }
        }
    }

    private fun initWebView() {
        web?.apply {
            webChromeClient = jsWebChromeClient
            setDefaultHandler(JsDefaultHandler())
            loadUrl("file:///android_asset/demo.html")
            //注册Js回调
            registerHandler("submitFromWeb") { data, function ->
                e("JS(submitFromWeb)回调 $data")
            }

            registerHandler("closeActivity"){ data, function ->
                this@MainActivity.finish()
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        jsWebChromeClient.onActivityResult(requestCode,resultCode,data)
    }
}
