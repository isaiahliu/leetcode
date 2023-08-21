package p05xx

import util.expect

fun main() {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    class Solution {
        fun maxDepth(root: Node?): Int {
            root ?: return 0

            return root.children.map { maxDepth(it) }.max() + 1
        }
    }

    expect {
        Solution().maxDepth(
            null
        )
    }
}