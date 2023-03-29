package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    class Solution {
        fun postorder(root: Node?): List<Int> {
            val result = arrayListOf<Int>()

            fun Node.dfs() {
                children.forEach {
                    it?.dfs()
                }
                result.add(`val`)
            }

            root?.dfs()

            return result
        }
    }

    measureTimeMillis {
        Solution().postorder(null).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}