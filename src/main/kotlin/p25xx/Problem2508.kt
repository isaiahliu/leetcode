package p25xx

import util.expect

fun main() {
    class Solution {
        fun isPossible(n: Int, edges: List<List<Int>>): Boolean {
            val degrees = hashMapOf<Int, MutableSet<Int>>()

            edges.forEach { (from, to) ->
                degrees.computeIfAbsent(from) { hashSetOf(to) }.add(to)
                degrees.computeIfAbsent(to) { hashSetOf(from) }.add(from)
            }

            val oddNodes = degrees.filter { it.value.size % 2 == 1 }.keys.toIntArray()

            fun tryConnect(node1: Int, node2: Int, forbidden: Set<Int> = emptySet()): Int {
                return if (node1 !in degrees[node2].orEmpty()) {
                    1
                } else if ((1..n).any {
                        it !in forbidden && it !in degrees[node1].orEmpty() && it !in degrees[node2].orEmpty()
                    }) {
                    2
                } else {
                    999
                }
            }

            return when (oddNodes.size) {
                0 -> {
                    true
                }

                2 -> {
                    val (node1, node2) = oddNodes

                    tryConnect(node1, node2) <= 2
                }

                4 -> {
                    val (node1, node2, node3, node4) = oddNodes

                    tryConnect(node1, node2, setOf(node3, node4)) + tryConnect(
                        node3, node4, setOf(node1, node2)
                    ) <= 2 || tryConnect(node1, node3, setOf(node2, node4)) + tryConnect(
                        node2, node4, setOf(node1, node3)
                    ) <= 2 || tryConnect(node1, node4, setOf(node2, node3)) + tryConnect(
                        node2, node3, setOf(node1, node4)
                    ) <= 2
                }

                else -> {
                    false
                }
            }
        }
    }

    expect {
        Solution().isPossible(
            5, listOf(
                listOf(1, 3),
                listOf(1, 4),
                listOf(1, 5),
            )
        )
    }
}