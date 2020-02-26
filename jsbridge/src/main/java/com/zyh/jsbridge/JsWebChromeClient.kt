package com.zyh.jsbridge

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 *Time:2020/2/26
 *Author:zyh
 *Description:
 */
class JsWebChromeClient : WebChromeClient{

    private val context: Activity
    private val  FILECHOOSER_RESULTCODE_FOR_ANDROID = 1
    private val  FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2
    private var mUploadMessage: ValueCallback<Uri>? = null
    private var mUploadCallbackAboveL: ValueCallback<Array<Uri>>? = null

    constructor(context: Activity){
        this.context = context
    }

    fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
        openFileChooserImplForAndroid(uploadMsg)
    }

    fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String?) {
        openFileChooserImplForAndroid(uploadMsg)
    }

    fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String?, capture: String?) {
        openFileChooserImplForAndroid(uploadMsg)
    }

    override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>, fileChooserParams: FileChooserParams?): Boolean {
        openFileChooserImplForAndroid5(filePathCallback)
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
    }

    private fun openFileChooserImplForAndroid(uploadMsg: ValueCallback<Uri>) {
        mUploadMessage = uploadMsg
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "image/*"
        context.startActivityForResult(Intent.createChooser(i, "图片选择"), FILECHOOSER_RESULTCODE_FOR_ANDROID)
    }
    private fun openFileChooserImplForAndroid5(uploadMsg: ValueCallback<Array<Uri>>) {
        mUploadCallbackAboveL = uploadMsg
        val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
        contentSelectionIntent.type = "image/*"
        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "图片选择")
        context.startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5
        )
    }
    /**
     * 5.0以下 上传图片成功后的回调
     */
    private fun mUploadMessageForAndroid(intent: Intent?, resultCode: Int) {
        if (null == mUploadMessage) {
            return
        }
        val result =
            if (intent == null || resultCode != Activity.RESULT_OK) null else intent.data
        mUploadMessage?.onReceiveValue(result)
        mUploadMessage = null
    }

    /**
     * 5.0以上 上传图片成功后的回调
     */
    private fun mUploadMessageForAndroid5(intent: Intent?, resultCode: Int) {
        if (null == mUploadCallbackAboveL) {
            return
        }
        try {
            val result =
                if (intent == null || resultCode != Activity.RESULT_OK) null else intent.data
            if (result != null) {
                mUploadCallbackAboveL?.onReceiveValue(arrayOf(result))
            } else {
                mUploadCallbackAboveL?.onReceiveValue(arrayOf<Uri>())
            }
            mUploadCallbackAboveL = null
        } catch (e: Exception) {
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        when(requestCode){
            FILECHOOSER_RESULTCODE_FOR_ANDROID ->{
                mUploadMessageForAndroid(data, resultCode)
            }
            FILECHOOSER_RESULTCODE_FOR_ANDROID_5 ->{
                mUploadMessageForAndroid5(data, resultCode)
            }
        }
    }
}