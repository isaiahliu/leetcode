package p06xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun addOneRow(root: TreeNode?, `val`: Int, depth: Int): TreeNode? {
            fun TreeNode?.dfs(leftBranch: Boolean, d: Int): TreeNode? {
                return when {
                    d == depth -> {
                        return TreeNode(`val`).also {
                            if (leftBranch) {
                                it.left = this
                            } else {
                                it.right = this
                            }
                        }
                    }

                    this == null -> {
                        null
                    }

                    else -> {
                        left = left.dfs(true, d + 1)
                        right = right.dfs(false, d + 1)

                        this
                    }
                }
            }

            return root.dfs(true, 1)
        }
    }

    measureTimeMillis {
        Solution().addOneRow(null, 5, 4).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}