package p11xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun btreeGameWinningMove(root: TreeNode?, n: Int, x: Int): Boolean {
            val sizes = hashMapOf<TreeNode, Int>()

            var node: TreeNode? = null
            fun TreeNode.dfs(): Int {
                if (`val` == x) {
                    node = this
                }

                var size = 1

                left?.also {
                    size += it.dfs()
                }

                right?.also {
                    size += it.dfs()
                }

                sizes[this] = size

                return size
            }

            root?.dfs()

            var max = 0
            val totalSize = sizes[root] ?: 0

            node?.also {
                val currentSize = sizes[it] ?: 0

                max = totalSize - currentSize

                it.left?.let { sizes[it] }?.also {
                    max = max.coerceAtLeast(it)
                }

                it.right?.let { sizes[it] }?.also {
                    max = max.coerceAtLeast(it)
                }
            }

            return max * 2 > totalSize
        }
    }

    measureTimeMillis {
        Solution().btreeGameWinningMove(null, 1, 1).also { println(it) }
    }
}

