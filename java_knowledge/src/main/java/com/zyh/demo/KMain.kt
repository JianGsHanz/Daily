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

        //获取K节点的所有元素
        getK(root,3)
        //二叉树前序遍历
        traverse(root)
        //获取字符串中每个字符数量
        getCharCount("zhangjiangshan")

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
}