package p06xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun findTarget(root: TreeNode?, k: Int): Boolean {
            val set = hashSetOf<Int>()

            var result = false
            fun TreeNode.dfs() {
                if (result) {
                    return
                }

                if (k - `val` in set) {
                    result = true
                } else {
                    set.add(`val`)

                    left?.dfs()
                    right?.dfs()
                }
            }

            root?.dfs()

            return result
        }
    }

    expect {
        Solution().findTarget(
            null, 1
        )
    }
}