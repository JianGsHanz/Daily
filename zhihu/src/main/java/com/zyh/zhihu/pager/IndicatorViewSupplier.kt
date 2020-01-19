package com.zyh.zhihu.pager

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes


abstract class IndicatorViewSupplier {

    private var mHeaderView: View? = null
    private var mFooterView: View? = null

    private var mHeaderViewOverThreshold = -1
    private var mFooterViewOverThreshold = -1

    fun inflate(parent: ViewGroup) {
        if(mHeaderView == null) {
            mHeaderView = inflateHeaderView(parent, LayoutInflater.from(parent.context))
        }
        val headerView = mHeaderView ?: throw NullPointerException("headerView must not be null!")
        measureChild(parent, headerView)
        val headerHeight = headerView.measuredHeight
        if(headerView.parent !is NoteDetailViewPager) {
            (headerView.parent as? ViewGroup)?.removeView(headerView)
            parent.addView(headerView, headerView.layoutParams.apply {
                if(this@apply is ViewGroup.MarginLayoutParams) {
                    topMargin = -headerHeight
                }
            })
        }

        if(mFooterView == null) {
            mFooterView = inflateFooterView(parent, LayoutInflater.from(parent.context))
        }
        val footerView = mFooterView ?: throw NullPointerException("footerView must not be null!")
        measureChild(parent, footerView)
        val footerHeight = footerView.measuredHeight
        if(footerView.parent !is NoteDetailViewPager) {
            (footerView.parent as? ViewGroup)?.removeView(footerView)
            parent.addView(footerView, footerView.layoutParams.apply {
                if(this@apply is ViewGroup.MarginLayoutParams) {
                    topMargin = -footerHeight
                }
            })
        }
    }

    fun layoutHeaderView(isOverThreshold: Boolean, layoutY: Float, pagerPosition: Int) {
        val headerView = mHeaderView ?: return
        val isOverThresholdInt = if(isOverThreshold) 1 else 0
        if(mHeaderViewOverThreshold != isOverThresholdInt) {
            bindHeaderView(headerView, isOverThreshold, pagerPosition)
            mHeaderViewOverThreshold = isOverThresholdInt
        }
        headerView.translationY = layoutY
    }

    fun layoutFooterView(isOverThreshold: Boolean, layoutY: Float, pagerPosition: Int) {
        val footerView = mFooterView ?: return
        val isOverThresholdInt = if(isOverThreshold) 1 else 0
        if(mFooterViewOverThreshold != isOverThresholdInt) {
            bindFooterView(footerView, isOverThreshold, pagerPosition)
            mFooterViewOverThreshold = isOverThresholdInt
        }
        footerView.translationY = layoutY + footerView.measuredHeight
    }

    private fun measureChild(parent: ViewGroup, childView: View) {
        childView.measure(View.MeasureSpec.makeMeasureSpec(parent.measuredWidth, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(parent.measuredHeight, View.MeasureSpec.AT_MOST))
    }

    @Suppress("unchecked_cast")
    protected fun <T : View> View.getView(@IdRes id: Int): T {
        if(tag == null) {
            tag = SparseArray<View>()
        }
        val viewCache = tag as SparseArray<View>
        var targetView: View? = viewCache.get(id)
        if (targetView == null) {
            targetView = findViewById(id)
            viewCache.put(id, targetView)
        }
        return targetView as T
    }

    fun resetLayout() {
        mHeaderViewOverThreshold = -1
        mFooterViewOverThreshold = -1
        mHeaderView?.run { translationY = 0F }
        mFooterView?.run { translationY = 0F }
    }

    fun recycler() {
        mHeaderView?.run {
            if(parent != null) {
                (parent as ViewGroup).removeView(this@run)
            }
        }
        mFooterView?.run {
            if(parent != null) {
                (parent as ViewGroup).removeView(this@run)
            }
        }
        mHeaderView = null
        mFooterView = null
    }

    protected abstract fun inflateHeaderView(parent: ViewGroup, inflater: LayoutInflater): View

    protected abstract fun inflateFooterView(parent: ViewGroup, inflater: LayoutInflater): View

    protected abstract fun bindHeaderView(view: View, isOverThreshold: Boolean, pagerPosition: Int)

    protected abstract fun bindFooterView(view: View, isOverThreshold: Boolean, pagerPosition: Int)
}