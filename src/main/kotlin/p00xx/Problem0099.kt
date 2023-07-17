package p00xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun recoverTree(root: TreeNode?): Unit {
            var leftNode: TreeNode? = null
            var rightNode: TreeNode? = null

            var previous: TreeNode? = null

            fun TreeNode.visit() {
                previous?.also {
                    when {
                        leftNode == null && it.`val` > this.`val` -> {
                            leftNode = previous
                        }

                        rightNode == null && leftNode?.takeIf { this.`val` > it.`val` } != null -> {
                            rightNode = previous
                        }
                    }
                }
                previous = this
            }

            fun TreeNode.walk() {
                if (rightNode != null) {
                    return
                }

                left?.walk()
                visit()
                right?.walk()
            }

            root?.walk()

            if (rightNode == null) {
                rightNode = previous
            }

            val t = leftNode?.`val`
            leftNode?.`val` = rightNode?.`val` ?: 0
            rightNode?.`val` = t ?: 0
        }
    }

    measureTimeMillis {
        Solution().recoverTree(
            TreeNode(68, TreeNode(41))
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

