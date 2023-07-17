package p01xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun flatten(root: TreeNode?): Unit {
            fun TreeNode.walk(): TreeNode {
                val leftNode = left
                val rightNode = right

                var leftTail = this
                if (leftNode != null) {
                    this.left = null
                    this.right = leftNode
                    leftTail = leftNode.walk()
                }

                var rightTail = leftTail
                if (rightNode != null) {
                    leftTail.right = rightNode
                    rightTail = rightNode.walk()
                }

                return rightTail
            }
            root?.walk()
        }
    }

    measureTimeMillis {
        Solution().flatten(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

