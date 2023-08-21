package p01xx

import util.TreeNode
import util.expect

fun main() {
    class Solution {
        fun rightSideView(root: TreeNode?): List<Int> {
            val map = hashMapOf<Int, Int>()

            fun TreeNode.walk(depth: Int = 0) {
                if (depth >= 0) {
                    map[depth] = `val`
                }

                left?.walk(depth + 1)
                right?.walk(depth + 1)
            }

            root?.walk()

            return map.entries.sortedBy { it.key }.map { it.value }
        }
    }

    expect {
        Solution().rightSideView(
            null
        )
    }
}

