package p09xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isCousins(root: TreeNode?, x: Int, y: Int): Boolean {
            val xParentMap = hashMapOf<Int, MutableSet<TreeNode>>()
            val yParentMap = hashMapOf<Int, MutableSet<TreeNode>>()

            fun TreeNode.dfs(depth: Int) {
                arrayOf(left, right).filterNotNull().forEach {
                    if (it.`val` == x) {
                        xParentMap.computeIfAbsent(depth) { hashSetOf() }.add(this)
                    }

                    if (it.`val` == y) {
                        yParentMap.computeIfAbsent(depth) { hashSetOf() }.add(this)
                    }
                }

                left?.dfs(depth + 1)
                right?.dfs(depth + 1)
            }

            root?.dfs(0)
            for (depth in xParentMap.keys) {
                val xParents = xParentMap[depth].orEmpty()
                val yParents = yParentMap[depth].orEmpty()

                if (xParents.isNotEmpty() && yParents.isNotEmpty() && (xParents + yParents).size > 1) {
                    return true
                }
            }

            return false
        }
    }

    measureTimeMillis {
        Solution().isCousins(
            TreeNode(
                1,
                TreeNode(2, TreeNode(3)),
                TreeNode(3)
            ), 1, 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
