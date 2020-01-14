package com.zyh.pop

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.PopupWindow

/**
 * Time:2020/1/14
 * Author:zyh
 * Description:popupWindow 的管理类
 */
class PopupController constructor(private val context: Context, private val mPopupWindow: PopupWindow) {
    private var layoutResId = 0 //布局id = 0
    private var mView: View? = null //用来区分setView的参数是layoutID 还是View
    private var mWindow: Window? = null //当前activity的window对象
    var mPopupView: View? = null //弹窗布局View

    fun setView(layoutResId: Int) {
        mView = null
        this.layoutResId = layoutResId
        installContent()
    }

    fun setView(view: View?) {
        mView = view
        layoutResId = 0
        installContent()
    }

    private fun installContent() {
        if (layoutResId != 0) {
            mPopupView = LayoutInflater.from(context).inflate(layoutResId, null)
        } else if (mView != null) {
            mPopupView = mView
        }
        mPopupWindow.contentView = mPopupView
    }

    /**
     * 设置宽度
     *
     * @param width  宽
     * @param height 高
     */
    private fun setWidthAndHeight(width: Int, height: Int) {
        if (width == 0 || height == 0) { //如果没设置宽高，默认是WRAP_CONTENT
            mPopupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
            mPopupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        } else {
            mPopupWindow.width = width
            mPopupWindow.height = height
        }
    }

    /**
     * 设置背景灰色程度
     *
     * @param level 0.0f-1.0f
     */
    fun setBackGroundLevel(level: Float) {
        mWindow = (context as Activity).window
        val params = mWindow!!.attributes
        params.alpha = level
        mWindow!!.attributes = params
    }

    /**
     * 设置动画
     */
    private fun setAnimationStyle(animationStyle: Int) {
        mPopupWindow.animationStyle = animationStyle
    }

    /**
     * 设置Outside是否可点击
     *
     * @param touchable 是否可点击
     */
    private fun setOutsideTouchable(touchable: Boolean) {
        mPopupWindow.setBackgroundDrawable(ColorDrawable(0x00000000)) //设置透明背景
        mPopupWindow.isOutsideTouchable = touchable //设置outside可点击
        mPopupWindow.isFocusable = touchable
    }

    //弹框的参数
    class PopupParams(var mContext: Context) {
        var layoutResId = 0 //布局id = 0
        var mWidth = 0
        var mHeight = 0 //弹窗的宽和高 = 0
        var animationStyle = 0 //动画Id = 0
        var isShowBg = false
        var isShowAnim = false
        var bg_level = 0f //屏幕背景灰色程度 = 0f
        var mView: View? = null
        var isTouchable = true //窗口外部是否可点击或点击消失
        fun apply(controller: PopupController) {
            when {
                mView != null -> {
                    controller.setView(mView)
                }
                layoutResId != 0 -> {
                    controller.setView(layoutResId)
                }
                else -> {
                    throw IllegalArgumentException("PopupView's contentView is null")
                }
            }
            controller.setWidthAndHeight(mWidth, mHeight)
            controller.setOutsideTouchable(isTouchable) //设置outside可点击
            if (isShowBg) { //设置背景
                controller.setBackGroundLevel(bg_level)
            }
            if (isShowAnim) {
                controller.setAnimationStyle(animationStyle)
            }
        }

    }

}