package p09xx

import util.TreeNode
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minCameraCover(root: TreeNode?): Int {
            val parents = hashMapOf<TreeNode, TreeNode>()
            val depths = hashMapOf<Int, MutableSet<TreeNode>>()

            var d = -1
            fun TreeNode.dfs(parent: TreeNode?, depth: Int) {
                parent?.also { parents[this] = it }

                depths.computeIfAbsent(depth) { hashSetOf() }.add(this)
                d = d.coerceAtLeast(depth)

                left?.dfs(this, depth + 1)
                right?.dfs(this, depth + 1)
            }

            val cameras = hashSetOf<TreeNode>()

            root?.dfs(null, 0)

            for (depth in d downTo 0) {
                depths[depth]?.forEach { node ->
                    parents[node]?.also {
                        cameras.add(it)
                        depths[depth - 1]?.remove(it)

                        parents[it]?.also {
                            depths[depth - 2]?.remove(it)
                        }
                    } ?: run {
                        cameras.add(node)
                    }
                }
            }

            return cameras.size
        }
    }

    measureTimeMillis {
        Solution().minCameraCover(
            TreeNode(
                0,
                TreeNode(
                    1,
                    TreeNode(2, TreeNode(3))
                )
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
