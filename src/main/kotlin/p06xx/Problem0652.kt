package p06xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
            val visited = hashSetOf<String>()
            val added = hashSetOf<String>()

            val result = arrayListOf<TreeNode>()

            fun TreeNode.dfs(): String {
                val serialize = "${`val`}[${left?.dfs() ?: "null"},${right?.dfs() ?: "null"}]"

                if (!visited.add(serialize) && added.add(serialize)) {
                    result.add(this)
                }

                return serialize
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().findDuplicateSubtrees(
            null
        )
    }
}