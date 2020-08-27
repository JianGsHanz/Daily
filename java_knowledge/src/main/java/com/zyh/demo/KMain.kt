package com.zyh.demo

import java.util.*
import kotlin.collections.LinkedHashMap

/**
 *Time:2020/2/19
 *Author:zyh
 *Description:
 */
object KMain {
    @JvmStatic
    fun main(arr: Array<String>){

        val arr = arrayOf(122,1,34,23,5,230,4)

//        val map = MyHashMap<String,String>()
//        for (i in 0 until 10){
//            map.put("张三$i","大哥哥$i")
//        }

//        print(map.size())

        val root = TreeNode(0)
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(5)

        root.left = node1
        root.right = node2
        node2.left = node3
        node2.right = node4

//        //获取K节点的所有元素
//        getK(root,3)
//        //二叉树前序遍历
//        traverse(root)
//        //获取字符串中每个字符数量
//        getCharCount("zhangjiangshan")
        //冒泡排序
//        bubble(arr)

        quickSort(arr,0,arr.size - 1)
        for (i in arr.indices){
            println(arr[i])
        }
    }


    //获取K节点的所有元素
    private fun getK(root: TreeNode, k : Int){
        if (root == null|| k <= 0){
            return
        }
        val queue = LinkedList<TreeNode>()
        queue.add(root)
        var count = 0
        while (!queue.isEmpty()){
            val size = queue.size
            if (count == k - 1){
                for (i in 0 until size){
                    println("k = " +queue[i])
                }
            }

            for (i in 0 until size){
                val head = queue.poll()
                if (head.left != null){
                    queue.offer(head.left)
                }
                if (head.right != null){
                    queue.offer(head.right)
                }
            }
            count++
        }
    }

    //二叉树前序遍历
    private fun traverse(node: TreeNode){
        if(node == null) return
        println(node.domain)
        node.left?.let { traverse(it) }
        node.right?.let { traverse(it) }
    }

    //获取字符串中每个字符数量
    private fun getCharCount(content: String){
        val list = LinkedHashMap<Char,Int>()

        for (i in content.toCharArray()){
            val oldValue = list[i]

            val newValue = if (oldValue == null){
                1
            }else{
                oldValue+1
            }
            list[i] = newValue
        }
        println(list)
    }
    //冒泡排序
    private fun bubble(arr: Array<Int>){
        for (i in 0 until arr.size - 1){
            for (j in 0 until arr.size - i -1){
                if(arr[j] > arr[j+1]){
                    val temp = arr[j]
                    arr[j] = arr[j+1]
                    arr[j+1] = temp
                }
            }
        }

        for (i in arr.indices){
            println(arr[i])
        }
    }

    private fun quickSort(arr: Array<Int>?, start: Int, end: Int) {
        if (arr.isNullOrEmpty() || start > end) return
        var i = start
        var j = end
        var temp = 0
        var standard = arr[start] //基准位
                 //122,1,34,23,5,230,4,2
        while (i < j){
            //先看右边，依次往左递减
            while (arr[j] >= standard && i < j){
                j--
            }
            if (i < j){
                temp = arr[j]
                arr[j] = arr[i]
                arr[i] = temp
            }
            //再看左边，依次往右递增
            while (arr[i] <= standard && i < j){
                i++
            }
            //如果满足条件则交换
            if (i < j){
              temp = arr[j]
              arr[j] = arr[i]
              arr[i] = temp
            }

            //递归调用左半数组
            quickSort(arr, start, i-1)
            //递归调用右半数组
            quickSort(arr, j+1, end)
        }

    }
}