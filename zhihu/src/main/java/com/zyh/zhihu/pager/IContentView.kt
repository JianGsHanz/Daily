package com.zyh.zhihu.pager

import android.view.View


interface IContentView {

    fun getView(): View

    /**
     * 检查该视图是否可以在某个方向上垂直滚动
     * @param direction 负数表示向上滚动，正数表示向下滚动
     */
    fun canScrollVertically(direction: Int): Boolean

    companion object Direction {

        const val SCROLL_UP = -1

        const val SCROLL_DOWN = 1
    }
}