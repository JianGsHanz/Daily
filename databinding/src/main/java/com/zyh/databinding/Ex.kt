package com.zyh.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide

/**
 * 动态改变ImageView，需通过注解@BindingAdapter自定义属性,
 *  且必须是顶层函数或静态方法,kotlin 必须加 apply plugin: 'kotlin-kapt'
 */
@BindingAdapter("url")
fun loadImage(view:ImageView,url:String){
    Glide.with(view.context).load(url).override(200,200).into(view)
}

/**
 * 这个是 databinding高级用法中，配合演示swipeRefreshLayout的刷新状态的感知
 * 第一步：单向的，数据变化，刷新UI
 */
@BindingAdapter("sfl_refreshing", requireAll = false)
fun setSwipeRefreshing(view: SwipeRefreshLayout, oldValue: Boolean, newValue: Boolean) {
    //判断是否是新的值，避免陷入死循环
    if (oldValue != newValue)
        view.isRefreshing = newValue

}

/**
 * 第二步：观察ui的状态，反向绑定给数据变化
 * 每当swipeRefreshLayout刷新状态被用户的操作改变，我们都能够在这里监听到，
 * 并交给InverseBindingListener这个 信使 去通知DataBinding
 */
@BindingAdapter("sfl_refreshingAttrChanged", requireAll = false)
fun setRefreshCallback(view: SwipeRefreshLayout, listener: InverseBindingListener?) {

    listener ?: return
    view.setOnRefreshListener {
        //由ui层的刷新状态变化，反向通知数据层的变化
        listener.onChange()
    }
}

/**
 * 反向绑定的实现，将UI的变化，回调给bindingListener，listener就会onChange，通知数据变化
 * 注意这里的attr和event，是跟上面两步配合一致才有效
 */
@InverseBindingAdapter(attribute = "sfl_refreshing", event = "sfl_refreshingAttrChanged")
fun isSwipeRefreshing(view: SwipeRefreshLayout): Boolean {
    return view.isRefreshing
}