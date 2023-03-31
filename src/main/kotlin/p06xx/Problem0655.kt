package p06xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun printTree(root: TreeNode?): List<List<String>> {
            fun TreeNode.maxDepth(): Int {
                return 1 + (left?.maxDepth() ?: 0).coerceAtLeast(right?.maxDepth() ?: 0)
            }

            val depth = root?.maxDepth() ?: 0

            val width = (1 shl depth) - 1

            val result = List(depth) {
                MutableList(width) { "" }
            }

            fun TreeNode.dfs(d: Int, startIndex: Int) {
                val w = (1 shl (depth - d)) - 1
                val pos = startIndex + w / 2

                result[d][pos] = `val`.toString()

                left?.dfs(d + 1, startIndex)
                right?.dfs(d + 1, startIndex + w / 2 + 1)
            }

            root?.dfs(0, 0)
            return result
        }
    }

    measureTimeMillis {
        Solution().printTree(
            TreeNode(
                1,
                TreeNode(
                    2,
                    null, TreeNode(4)
                ),
                TreeNode(3),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}