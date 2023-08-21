package p08xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
            val route = arrayListOf<TreeNode>()
            fun TreeNode.findRoute(): Boolean {
                if (this == target || left?.findRoute() == true || right?.findRoute() == true) {
                    route.add(this)

                    return true
                }

                return false
            }


            root?.findRoute()

            route.map { it.`val` }
            val result = arrayListOf<Int>()

            fun TreeNode.dfs(distance: Int) {
                when {
                    distance > k -> {
                        return
                    }

                    distance == k -> {
                        result.add(`val`)
                        return
                    }

                    else -> {
                        left?.takeIf { it !in route }?.dfs(distance + 1)
                        right?.takeIf { it !in route }?.dfs(distance + 1)
                    }
                }
            }

            route.forEachIndexed { index, treeNode ->
                treeNode.dfs(index)

            }

            return result
        }
    }

    expect {
        Solution().distanceK(
            null, null, 0
        )

    }
}