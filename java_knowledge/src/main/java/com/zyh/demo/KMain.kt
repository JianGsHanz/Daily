package com.zyh.demo

/**
 *Time:2020/2/19
 *Author:zyh
 *Description:
 */
object KMain {

    @JvmStatic
    fun main(arr: Array<String>){

        val map = MyHashMap<String,String>()
        for (i in 0 until 10){
            map.put("张三$i","大哥哥$i")
        }

        print(map.size())
    }
}