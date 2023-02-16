package p00xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun generateTrees(n: Int): List<TreeNode?> {
            fun walk(min: Int, max: Int): List<TreeNode?> {
                if (min == max) {
                    return listOf(TreeNode(min))
                } else if (min > max) {
                    return listOf(null)
                } else {
                    val result = arrayListOf<TreeNode>()

                    for (root in min..max) {
                        val lefts = walk(min, root - 1)
                        val rights = walk(root + 1, max)

                        lefts.forEach { left ->
                            rights.forEach { right ->
                                TreeNode(root).also {
                                    it.left = left
                                    it.right = right

                                    result.add(it)
                                }
                            }
                        }
                    }

                    return result
                }
            }

            return walk(1, n)
        }
    }

    measureTimeMillis {
        Solution().generateTrees(
            3
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

