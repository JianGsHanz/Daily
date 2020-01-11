package com.zyh.viewpage2

/**
 *Time:2019/11/5
 *Author:zyh
 *Description:
 */
class TMain {
    companion object{
        @JvmStatic
        fun main(args : Array<String>) {
            val b = BaseImpl(10)
            Derived(b).printMessage()
            Derived(b).printMessageLine()
            // 运行结果：abc10
            // 由于 printMessage 被 Derived 重写，不会执行委托类中的方法
            // printMessageLine 直接执行委托类中的方法

        }
    }
}


interface Base {
    fun printMessage()
    fun printMessageLine()
}

class BaseImpl(val x: Int) : Base {
    override fun printMessage() { print(x) }
    override fun printMessageLine() { println(x) }
}

// 将 Derived 委托给 b
class Derived(base: Base) : Base by base{
    // 重写其中一个方法
    override fun printMessage() { print("abc1") }
}
