package p04xx

import util.expect

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

    expect {
        Solution().levelOrder(
            null
        )
    }
}


