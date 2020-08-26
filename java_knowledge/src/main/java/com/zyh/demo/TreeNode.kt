package com.zyh.demo

class TreeNode {
    var domain = 0
    var left: TreeNode? = null
    var right: TreeNode? = null

    constructor(domain: Int){
        this.domain = domain
    }

    override fun toString(): String {
        return "TreeNode(domain=$domain, left=$left, right=$right)"
    }
}