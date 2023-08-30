package p21xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun createBinaryTree(descriptions: Array<IntArray>): TreeNode {
            val children = hashSetOf<Int>()

            val nodes = hashMapOf<Int, TreeNode>()

            descriptions.forEach { (parent, child, left) ->
                val p = nodes.computeIfAbsent(parent) { TreeNode(parent) }
                val c = nodes.computeIfAbsent(child) { TreeNode(child) }

                children.add(child)
                if (left == 1) {
                    p.left = c
                } else {
                    p.right = c
                }
            }

            children.forEach { nodes.remove(it) }

            return nodes.values.first()
        }
    }

    expect {
        Solution().createBinaryTree(
            arrayOf()
        )
    }
}