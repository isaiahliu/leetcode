package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Node(var `val`: Int) {
        var children: List<Node?> = listOf()
    }

    class Solution {
        fun levelOrder(root: Node?): List<List<Int>> {
            val result = arrayListOf<List<Int>>()

            var level = listOf(root)

            while (level.isNotEmpty()) {
                result.add(level.mapNotNull { it?.`val` }.ifEmpty {
                    return result
                })

                level = level.map { it?.children.orEmpty() }.flatten()
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().levelOrder(
            null
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}


