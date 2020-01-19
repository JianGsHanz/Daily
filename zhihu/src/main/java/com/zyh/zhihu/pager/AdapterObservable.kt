package com.zyh.zhihu.pager

class AdapterObservable<T: AdapterObserver> {

    var mObserver: T? = null

    fun registerObserver(observer: T?) {
        if (observer == null) {
            return
        }
        mObserver = observer
    }

    fun unregisterObserver() {
        mObserver = null
    }

}