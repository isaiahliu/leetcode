package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    class Solution {
        fun preorder(root: Node?): List<Int> {
            val result = arrayListOf<Int>()

            fun Node.dfs() {
                result.add(`val`)

                children.forEach {
                    it?.dfs()
                }
            }

            root?.dfs()

            return result
        }
    }

    measureTimeMillis {
        Solution().preorder(null).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}