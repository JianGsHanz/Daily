package com.zyh.zhihu.pager

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.*
import android.view.animation.Interpolator
import android.widget.FrameLayout
import android.widget.OverScroller
import java.util.*
import android.view.MotionEvent
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.zyh.zhihu.R


class NoteDetailViewPager: FrameLayout {

    companion object {
        private const val NO_POSITION = -1
    }

    private var mPosition: Int = NO_POSITION
    private var mPagerScrollY = 0

    private var mAdapter: IAdapter? = null

    private var mContentViewArray = LinkedList<IContentView>()
    enum class ContentViewAnchor {
        PREVIOUS, CENTER, NEXT
    }
    private fun getContentView(anchor: ContentViewAnchor): IContentView = when(anchor) {
        ContentViewAnchor.PREVIOUS -> mContentViewArray[0]
        ContentViewAnchor.CENTER -> mContentViewArray[1]
        ContentViewAnchor.NEXT -> mContentViewArray[2]
    }

    private val mInvalidPointer = -1
    private var mActivePointerId: Int = 0

    private var mDownX: Float = 0F
    private var mDownY: Float = 0F
    private var mTempX: Float = 0F
    private var mTempY: Float = 0F
    private val mSensitivity = 0.25F

    private val mTouchSlop by lazy {
        ViewConfiguration.get(context).scaledTouchSlop.times(mSensitivity).toInt()
    }
    private val mScroller: OverScroller by lazy {
        OverScroller(context, Interpolator { input ->
            var t = input
            t -= 1.0f
            t * t * t * t * t + 1.0f
        })
    }
    private val mScrollDuration = 350
    private var mSlideAnimator: ObjectAnimator? = null

    private var mIsDraggingUp = false
    private var mIsDraggingDown = false

    private val mDragDownThreshold = 650
    private val mDragDownDetermineThreshold = 300
    private val mDragUpThreshold = 650
    private val mDragUpDetermineThreshold = 300

    private var mIndicatorViewSupplier: IndicatorViewSupplier = object : IndicatorViewSupplier() {

        override fun inflateHeaderView(parent: ViewGroup, inflater: LayoutInflater): View {
            return inflater.inflate(R.layout.matrix_layout_note_detail_pager_header, parent, false)
        }

        override fun inflateFooterView(parent: ViewGroup, inflater: LayoutInflater): View {
            return inflater.inflate(R.layout.matrix_layout_note_detail_pager_footer, parent, false)
        }

        override fun bindHeaderView(view: View, isOverThreshold: Boolean, pagerPosition: Int) {
            view.getView<TextView>(R.id.remindTV).apply {
                text = when {
                    mPosition == 0 -> "没有更多了"
                    isOverThreshold -> "松开阅读上一篇笔记↑"
                    else -> "下拉阅读上一篇笔记↓"
                }
            }
        }

        override fun bindFooterView(view: View, isOverThreshold: Boolean, pagerPosition: Int) {
            val adapter = mAdapter?: return
            view.getView<TextView>(R.id.remindTV).apply {
                text = when {
                    mPosition == adapter.getItemCount() - 1 -> "没有更多了"
                    isOverThreshold -> "松开阅读下一篇笔记↓"
                    else -> "上拉阅读下一篇笔记↑"
                }
            }
        }
    }

    /**
     * 自定义下拉Header和上拉Footer.
     */
    fun setIndicatorViewSupplier(supplier: IndicatorViewSupplier) {
        mIndicatorViewSupplier.recycler()
        mIndicatorViewSupplier = supplier
        supplier.inflate(this@NoteDetailViewPager)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(context, attributeSet, defStyle) {
        setBackgroundColor(Color.WHITE)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val adapter = mAdapter
        if(adapter == null || adapter.getItemCount() <= 0) {
            return
        }

        mContentViewArray.forEachIndexed { index, contentView ->
            val view = contentView.getView()
            if(view.parent !is NoteDetailViewPager) {
                val parent = view.parent as? ViewGroup
                parent?.run { removeView(view) }

                addView(view, generatePagerLayoutParams())
            }

            val pagerHeight = bottom - top

            view.layout(left,
                    top + pagerHeight * (index - 1) + if(index == 1) mPagerScrollY else 0,
                    right,
                    top + pagerHeight * index + if(index == 1) mPagerScrollY else 0)
            // reset.
            view.apply {
                alpha = 1F
                translationY = 0F
            }
        }

        if(mPosition == NO_POSITION) {
            adapter.onBindContentView(getContentView(ContentViewAnchor.CENTER).getView(), 0)
            if(adapter.getItemCount() > 0) {
                adapter.onBindContentView(getContentView(ContentViewAnchor.NEXT).getView(), 1)
            }
            mPosition = 0
            mPagerScrollY = 0
        }
    }

    private fun generatePagerLayoutParams(): FrameLayout.LayoutParams {
        return LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    fun setAdapter(adapter: Adapter?) {
        if(adapter == null) {
            return
        }
        mAdapter = adapter
        adapter.registerObserver(internalObserver)

        val inflater = LayoutInflater.from(context)
        for(index in 0 until 3) {
            mContentViewArray.add(adapter.onCreateContentView(this, inflater))
        }

        post {
            mIndicatorViewSupplier.recycler()
            mIndicatorViewSupplier.inflate(this@NoteDetailViewPager)
        }
    }

    private val internalObserver by lazy(LazyThreadSafetyMode.NONE) {
        object : AdapterObserver() {
            override fun onChanged() {
                mPosition = NO_POSITION
                mPagerScrollY = 0
                requestLayout()
            }
        }
    }

    internal interface IAdapter {

        fun onCreateContentView(parent: ViewGroup, inflater: LayoutInflater): IContentView

        fun onBindContentView(itemView: View, position: Int)

        fun getItemCount(): Int

        fun notifyDataSetChanged()

        fun registerObserver(observer: AdapterObserver?)

        fun unregisterObserver()
    }

    abstract class Adapter: IAdapter {

        private val adapterObservable by lazy(LazyThreadSafetyMode.NONE) {
            AdapterObservable<AdapterObserver>()
        }

        final override fun notifyDataSetChanged() {
            adapterObservable.mObserver?.onChanged()
        }

        final override fun registerObserver(observer: AdapterObserver?) {
            adapterObservable.registerObserver(observer)
        }

        final override fun unregisterObserver() {
            adapterObservable.unregisterObserver()
        }
    }

    override fun requestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//        super.requestDisallowInterceptTouchEvent(disallowIntercept)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if(isEnabled.not()) {
            return super.onInterceptTouchEvent(ev)
        }
        val action = ev.actionMasked
        val pointerIndex: Int

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mActivePointerId = ev.getPointerId(0)
                pointerIndex = ev.findPointerIndex(mActivePointerId)
                if (pointerIndex < 0) {
                    return false
                }

                mTempX = ev.getX(pointerIndex)
                mTempY = ev.getY(pointerIndex)
                mDownX = mTempX
                mDownY = mTempY

                mIsDraggingDown = false
                mIsDraggingUp = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (mActivePointerId == mInvalidPointer) {
                    return false
                }
                pointerIndex = ev.findPointerIndex(mActivePointerId)
                if (pointerIndex < 0) {
                    return false
                }

                val moveX = ev.getX(pointerIndex)
                val moveY = ev.getY(pointerIndex)

                val xDiff = Math.abs(moveX - mDownX)
                val yDiff = Math.abs(moveY - mDownY)
                if (yDiff > mTouchSlop && yDiff > xDiff) {
                    tryDragging(moveY)
                }
                mTempX = moveX
                mTempY = moveY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                onSecondaryPointerUp(ev)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                determineNextStep()
                resetTouchValue()
            }
        }
        return mIsDraggingDown || mIsDraggingUp || super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if(isEnabled.not()) {
            return super.onTouchEvent(ev)
        }
        val action = ev.actionMasked
        val pointerIndex: Int

        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mActivePointerId = ev.getPointerId(0)
                pointerIndex = ev.findPointerIndex(mActivePointerId)
                if (pointerIndex < 0) {
                    return false
                }

                mTempX = ev.getX(pointerIndex)
                mTempY = ev.getY(pointerIndex)
                mDownX = mTempX
                mDownY = mTempY

                mIsDraggingDown = false
                mIsDraggingUp = false
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (mActivePointerId == mInvalidPointer) {
                    return false
                }
                pointerIndex = ev.findPointerIndex(mActivePointerId)
                if (pointerIndex < 0) {
                    return false
                }

                val moveX = ev.getX(pointerIndex)
                val moveY = ev.getY(pointerIndex)

                val xDiff = Math.abs(moveX - mDownX)
                val yDiff = Math.abs(moveY - mDownY)
                if (yDiff > mTouchSlop && yDiff > xDiff) {
                    tryDragging(moveY)
                }
                if(mIsDraggingDown || mIsDraggingUp) {
                    offsetContentView((moveY - mTempY).toInt())
                }
                mTempX = moveX
                mTempY = moveY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                onSecondaryPointerUp(ev)
                if(mIsDraggingDown || mIsDraggingUp) {
                    if (mActivePointerId == mInvalidPointer) {
                        return false
                    }
                    pointerIndex = ev.findPointerIndex(mActivePointerId)
                    if (pointerIndex < 0) {
                        return false
                    }
                    mTempX = ev.getX(pointerIndex)
                    mTempY = ev.getY(pointerIndex)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                determineNextStep()
                resetTouchValue()
            }
        }

        return mIsDraggingDown || mIsDraggingUp || super.onTouchEvent(ev)
    }

    private fun tryDragging(moveY: Float) {
        val contentView = getContentView(ContentViewAnchor.CENTER)
        if(moveY > mDownY) {
            if(!mIsDraggingDown && !contentView.canScrollVertically(IContentView.SCROLL_UP)) {
                mIsDraggingDown = true
            }
        } else if(moveY < mDownY) {
            if(!mIsDraggingUp && !contentView.canScrollVertically(IContentView.SCROLL_DOWN)) {
                mIsDraggingUp = true
            }
        }
        if(mIsDraggingDown || mIsDraggingUp) {
            resetScroller()
        }
    }

    private fun onSecondaryPointerUp(ev: MotionEvent) {
        val pointerIndex = ev.actionIndex
        val pointerId = ev.getPointerId(pointerIndex)
        if (pointerId == mActivePointerId) {
            val newPointerIndex = if (pointerIndex == 0) 1 else 0
            mActivePointerId = ev.getPointerId(newPointerIndex)
        }
    }

    private fun resetTouchValue() {
        mIsDraggingDown = false
        mIsDraggingUp = false
        mActivePointerId = mInvalidPointer
    }

    private fun determineNextStep() {
        val adapter = mAdapter ?: return
        val contentView = getContentView(ContentViewAnchor.CENTER).getView()
        if(mIsDraggingDown) {
            if(contentView.top <= mDragDownDetermineThreshold || mPosition == 0) {// 回位.
                mScroller.startScroll(0, contentView.top, 0, -contentView.top, mScrollDuration)
                ViewCompat.postInvalidateOnAnimation(this)
            } else {// 向上翻页.
                slideToTargetPage(false)
            }
        } else if(mIsDraggingUp) {
            val pagerHeight = measuredHeight
            if(pagerHeight - contentView.bottom <= mDragUpDetermineThreshold || mPosition == adapter.getItemCount() - 1) {// 回位.
                mScroller.startScroll(0, contentView.top, 0, -contentView.top, mScrollDuration)
                ViewCompat.postInvalidateOnAnimation(this)
            } else {// 向下翻页.
                slideToTargetPage(true)
            }
        }
    }

    private fun slideToTargetPage(next: Boolean) {
        val adapter = mAdapter ?: return
        isEnabled = false
        mPagerScrollY = 0
        mIndicatorViewSupplier.resetLayout()
        resetSlideAnimator()

        val pagerHeight = measuredHeight
        val contentView = getContentView(ContentViewAnchor.CENTER).getView()
        contentView.alpha = 1F
        contentView.animate().alpha(0F)
                .setDuration(200)
                .setInterpolator(AccelerateInterpolator())
                .start()
        if(next) {
            val nextContentView = getContentView(ContentViewAnchor.NEXT).getView()
            mSlideAnimator = ObjectAnimator.ofFloat(nextContentView, "translationY", -pagerHeight.toFloat())
                    .apply {
                        duration = 300
                        interpolator = AccelerateDecelerateInterpolator()
                        addListener(object: AnimatorListenerAdapter() {

                            override fun onAnimationEnd(animation: Animator?) {
                                this@NoteDetailViewPager.isEnabled = true
                                mPosition++

                                val tempPreviousContentView = mContentViewArray[0]
                                mContentViewArray[0] = mContentViewArray[1]
                                mContentViewArray[1] = mContentViewArray[2]
                                mContentViewArray[2] = tempPreviousContentView
                                adapter.onBindContentView(tempPreviousContentView.getView(), mPosition + 1)
                                requestLayout()
                            }
                        })
                        start()
                    }
        } else {
            val previousContentView = getContentView(ContentViewAnchor.PREVIOUS).getView()
            mSlideAnimator = ObjectAnimator.ofFloat(previousContentView, "translationY", pagerHeight.toFloat())
                    .apply {
                        duration = 300
                        interpolator = AccelerateDecelerateInterpolator()
                        addListener(object: AnimatorListenerAdapter() {

                            override fun onAnimationEnd(animation: Animator?) {
                                this@NoteDetailViewPager.isEnabled = true
                                mPosition--

                                val tempPreviousContentView = mContentViewArray[2]
                                mContentViewArray[2] = mContentViewArray[1]
                                mContentViewArray[1] = mContentViewArray[0]
                                mContentViewArray[0] = tempPreviousContentView
                                adapter.onBindContentView(tempPreviousContentView.getView(), mPosition - 1)
                                requestLayout()
                            }
                        })
                        start()
                    }
        }
    }

    override fun computeScroll() {
        if(mScroller.computeScrollOffset()) {
            val contentView = getContentView(ContentViewAnchor.CENTER).getView()
            mPagerScrollY = mScroller.currY
            ViewCompat.offsetTopAndBottom(contentView, mScroller.currY - contentView.top)
            ViewCompat.postInvalidateOnAnimation(this)

            layoutIndicatorView()
        }
    }

    private fun offsetContentView(offset: Int) {
        val contentView = getContentView(ContentViewAnchor.CENTER).getView()
        var computeOffset = 0
        var doNotElastic = false
        if(mIsDraggingDown) {
            if (offset > 0 && contentView.top < mDragDownThreshold) {
                computeOffset = when {
                    contentView.top + offset > mDragDownThreshold -> {
                        doNotElastic = true
                        mDragDownThreshold - contentView.top
                    }
                    else -> offset
                }
            } else if(offset < 0 && contentView.top > 0) {
                computeOffset = when {
                    contentView.top + offset < 0 -> {
                        doNotElastic = true
                        -contentView.top
                    }
                    else -> offset
                }
            }
        }
        if(mIsDraggingUp) {
            val pagerHeight = measuredHeight
            if (offset < 0 && (pagerHeight - contentView.bottom) < mDragUpThreshold) {
                computeOffset = when {
                    pagerHeight - contentView.bottom - offset > mDragUpThreshold -> {
                        doNotElastic = true
                        pagerHeight - contentView.bottom - mDragUpThreshold
                    }
                    else -> offset
                }
            } else if(offset > 0 && measuredHeight > contentView.bottom) {
                computeOffset = when {
                    pagerHeight - contentView.bottom + offset < 0 -> {
                        doNotElastic = true
                        contentView.bottom - pagerHeight
                    }
                    else -> offset
                }
            }
        }
        if(computeOffset != 0) {
            val withElasticOffset: Double = if(doNotElastic) {
                computeOffset.toDouble()
            } else {
                computeOffset * Math.pow(computeCoefficientOfElasticity().toDouble(), 1.5)
            }
            mPagerScrollY = (contentView.top + withElasticOffset).toInt()
            ViewCompat.offsetTopAndBottom(contentView, withElasticOffset.toInt())

            layoutIndicatorView()
        }
    }

    private fun layoutIndicatorView() {
        val contentView = getContentView(ContentViewAnchor.CENTER).getView()
        mIndicatorViewSupplier.layoutHeaderView(
                contentView.top > mDragDownDetermineThreshold,
                mPagerScrollY.toFloat(), mPosition)
        val pagerHeight = measuredHeight
        mIndicatorViewSupplier.layoutFooterView(
                pagerHeight - contentView.bottom > mDragUpDetermineThreshold,
                contentView.bottom.toFloat(), mPosition)
    }

    private fun computeCoefficientOfElasticity(): Float {
        val contentView = getContentView(ContentViewAnchor.CENTER).getView()
        if(mIsDraggingDown) {
            return (mDragDownThreshold - contentView.top).toFloat() / mDragDownThreshold
        } else if(mIsDraggingUp) {
            return (mDragUpThreshold - (measuredHeight - contentView.bottom)).toFloat() / mDragUpThreshold
        }
        return 1F
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mAdapter?.unregisterObserver()

        resetScroller()
        resetSlideAnimator()
    }

    private fun resetScroller() {
        mScroller.run {
            if(isFinished.not()) abortAnimation()
        }
    }

    private fun resetSlideAnimator() {
        mSlideAnimator?.run {
            if(isRunning) cancel()
        }
    }

}