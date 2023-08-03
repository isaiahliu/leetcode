package p16xx

import util.TreeNode
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isEvenOddTree(root: TreeNode?): Boolean {
            root?.takeIf { it.`val` % 2 == 0 }?.also {
                return false
            }

            val nodes = LinkedList<TreeNode>()
            root?.also { nodes.add(it) }

            var lastBit = 1
            while (nodes.isNotEmpty()) {
                lastBit = lastBit xor 1
                var pre = if (lastBit == 0) Int.MAX_VALUE else Int.MIN_VALUE

                repeat(nodes.size) {
                    val node = nodes.poll()

                    val children = arrayListOf<TreeNode>()
                    node.left?.also { children.add(it) }
                    node.right?.also { children.add(it) }

                    children.forEach {
                        if ((it.`val` and 1) xor lastBit > 0) {
                            return false
                        }

                        pre = if (lastBit == 0) {
                            if (pre > it.`val`) {
                                it.`val`
                            } else {
                                return false
                            }
                        } else {
                            if (pre < it.`val`) {
                                it.`val`
                            } else {
                                return false
                            }
                        }

                        nodes.add(it)
                    }
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().isEvenOddTree(
            TreeNode(
                1,
                TreeNode(10),
                TreeNode(4)
            )
        ).also { println("${it} should be ${it}") }
    }
}

