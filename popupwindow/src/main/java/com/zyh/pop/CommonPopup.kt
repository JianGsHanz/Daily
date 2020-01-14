package com.zyh.pop

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.PopupWindow
import com.zyh.pop.PopupController.PopupParams

/**
 * Time:2020/1/14
 * Author:zyh
 * Description:
 */
class CommonPopup : PopupWindow {

    val mPopupController: PopupController
    constructor(context: Context){
        mPopupController = PopupController(context, this)
    }
    /**
     * 弹框的高度
     * @return
     */
    override fun getHeight(): Int {
        return super.getHeight()
    }

    /**
     * 弹框的宽度
     * @return
     */
    override fun getWidth(): Int {
        return super.getWidth()
    }

    /**
     * 弹框消失
     */
    override fun dismiss() {
        super.dismiss()
        mPopupController.setBackGroundLevel(1.0f) // 恢复透明度
    }
    //--------------------------------popupWindow的显示位置---------------------------------------

    @SuppressLint("NewApi")
    fun showNormal(parent: View?): CommonPopup {
        showAsDropDown(parent,  0, 0,Gravity.CENTER)
        return this
    }


    @SuppressLint("NewApi")
    fun showLeft(parent: View?): CommonPopup {
        showAsDropDown(parent,  0, 0,Gravity.LEFT)
        return this
    }


    @SuppressLint("NewApi")
    fun showUp(parent: View?): CommonPopup {
        showAsDropDown(parent,  0, 0,Gravity.TOP)
        return this
    }


    @SuppressLint("NewApi")
    fun showRight(parent: View?): CommonPopup {
        showAsDropDown(parent,  0, 0,Gravity.RIGHT)
        return this
    }

    /**
     * 展示在目标parent的左下方
     * @param parent
     * @param gravity
     * @return
     */
    @SuppressLint("NewApi")
    fun showBottom(parent: View?): CommonPopup {
        showAsDropDown(parent,  0, 0,Gravity.BOTTOM)
        return this
    }

    fun showParentBottom(parent: View?): CommonPopup {
        showAtLocation(parent,Gravity.BOTTOM,0,0)
        return this
    }

    fun getController():PopupController = mPopupController

    //Builder
    class Builder {
        private val param: PopupParams
        constructor(context: Context){
            param = PopupParams(context)
        }
        /**
         * 设置PopupWindow的布局ID
         * @param layoutResId
         * @return
         */
        fun setView(layoutResId: Int): Builder {
            param.mView = null //重复创建的时候将之前的View清除
            param.layoutResId = layoutResId
            return this
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        fun setView(view: View?): Builder {
            param.mView = view
            param.layoutResId = 0
            return this
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        fun setWidthAndHeight(width: Int, height: Int): Builder {
            param.mWidth = width
            param.mHeight = height
            return this
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        fun setBackGroundLevel(level: Float): Builder {
            param.isShowBg = true
            param.bg_level = level
            return this
        }

        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        fun setOutsideTouchable(touchable: Boolean): Builder {
            param.isTouchable = touchable
            return this
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        fun setAnimationStyle(animationStyle: Int): Builder {
            param.isShowAnim = true
            param.animationStyle = animationStyle
            return this
        }

        /**
         * popupWindow的创建
         * @return
         */
        fun create(): CommonPopup {
            val popupWindow = CommonPopup(param.mContext)
            param.apply(popupWindow.mPopupController)
            return popupWindow
        }
    }

}