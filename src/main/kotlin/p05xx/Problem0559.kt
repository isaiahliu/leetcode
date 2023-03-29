package p05xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().maxDepth(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}