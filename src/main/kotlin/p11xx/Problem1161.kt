package p11xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun maxLevelSum(root: TreeNode?): Int {
            val map = hashMapOf<Int, Int>()
            fun TreeNode.dfs(depth: Int) {
                map[depth] = (map[depth] ?: 0) + `val`

                left?.dfs(depth + 1)
                right?.dfs(depth + 1)
            }

            root?.dfs(1)

            return map.entries.minWith(compareByDescending<MutableMap.MutableEntry<Int, Int>> { it.value }.thenBy { it.key }).key
        }
    }

    expect {
        Solution().maxLevelSum(
            null
        )
    }
}