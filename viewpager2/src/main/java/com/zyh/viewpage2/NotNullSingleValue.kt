package com.zyh.viewpage2

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class NotNullSingleValue<T> : ReadWriteProperty<Any?, T> {
    // 默认为 null
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        // value 为 null 抛出异常
        return value ?: throw IllegalStateException("${property.name} " +
            "not initialized")
    }


    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        // 只有 value 是 null 的时候，才能 setValue
        // setValue 只能正确执行一次
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}