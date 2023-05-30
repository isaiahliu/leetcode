package p11xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
            val result = arrayListOf<TreeNode>()

            fun TreeNode.dfs(top: Boolean) {
                if (this.`val` in to_delete) {
                    left?.dfs(true)
                    right?.dfs(true)
                } else {
                    if (top) {
                        result.add(this)
                    }

                    left?.also {
                        if (it.`val` in to_delete) {
                            left = null
                        }
                        it.dfs(false)
                    }

                    right?.also {
                        if (it.`val` in to_delete) {
                            right = null
                        }
                        it.dfs(false)
                    }
                }
            }

            root?.dfs(true)

            return result
        }
    }

    measureTimeMillis {
        Solution().delNodes(
            null, intArrayOf()
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}