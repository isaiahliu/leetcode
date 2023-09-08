package p23xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun amountOfTime(root: TreeNode?, start: Int): Int {
            val badRoots = hashSetOf<TreeNode>()
            fun TreeNode.findBadNode(): Boolean {
                if (`val` == start || left?.findBadNode() == true || right?.findBadNode() == true) {
                    badRoots.add(this)
                    return true
                }

                return false
            }

            root?.findBadNode()

            var result = 0

            fun TreeNode.dfs(distance: Int) {
                var currentDistance = distance
                if (this in badRoots) {
                    currentDistance--
                } else {
                    currentDistance++
                }

                result = result.coerceAtLeast(currentDistance)

                left?.dfs(currentDistance)
                right?.dfs(currentDistance)
            }

            root?.dfs(badRoots.size)

            return result
        }
    }

    expect {
        Solution().amountOfTime(
            null, 1
        )
    }
}