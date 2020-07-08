package com.zyh.viewpage2

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

//    val instance by lazy{
//        this
//    }
//    var instances : MainActivity by NotNullSingleValue()

    var mGestureDetector: GestureDetector? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // by lazy委托需在{}返回实例
//        Log.e("MainActivity","ddd: $instance")
        //by委托需先赋值
//        instances = this
//        Log.e("MainActivity","ddd: $instances")

        //手势控制viewpager2太过灵敏
        mGestureDetector = GestureDetector(this, object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {

            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                return false
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return false
            }

            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {

                if (e1.x - e2.x > 100) {
                    Log.i("TAG", "向左滑...")
                    vp.beginFakeDrag()
                    if (vp.fakeDragBy(1f)) {
                        vp.endFakeDrag()
                    }
                    return true
                }
                if (e2.x - e1.x > 100) {
                    Log.i("TAG", "向右滑...")
                    vp.beginFakeDrag()
                    if (vp.fakeDragBy(-1f)) {
                        vp.endFakeDrag()
                    }
                    return true
                }
                if (e1.y - e2.y > 100) {
                    Log.i("TAG", "向上滑...")
                    return true
                }
                if (e2.y - e1.y > 100) {
                    Log.i("TAG", "向下滑...")
                    return true
                }

                return false
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return false
            }

            override fun onLongPress(e: MotionEvent?) {
            }
        })


        val list = mutableListOf<String>()

        (1..8).forEachIndexed { _, i ->
            list.add("tab$i")
        }

//        vp.adapter = ViewPage2Adapter(this,list)
//        vp.orientation = ViewPager2.ORIENTATION_VERTICAL  //垂直滚动
        val adapter = ViewPageFragmentAdapter(this)
        vp.isUserInputEnabled = false
        vp.adapter = adapter

        TabLayoutMediator(tabLayout, vp) { tab, position ->
            tab.text = list[position]
        }.run { attach() }

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        mGestureDetector!!.onTouchEvent(ev)
        super.dispatchTouchEvent(ev)
        return false
    }

}
