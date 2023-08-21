package p08xx

import util.expect

fun main() {
    class Solution {
        fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {
            val terminals = hashSetOf<Int>()

            val froms = Array(graph.size) { hashSetOf<Int>() }

            val graphTos = graph.mapIndexed { index, tos ->
                if (tos.isEmpty()) {
                    terminals.add(index)
                }

                tos.forEach {
                    froms[it].add(index)
                }

                tos.size
            }.toIntArray()

            val result = sortedSetOf<Int>()

            while (terminals.isNotEmpty()) {
                terminals.toSet().also { terminals.clear() }.forEach { t ->
                    result += t

                    froms[t].forEach { from ->
                        graphTos[from]--
                        if (graphTos[from] == 0) {
                            terminals.add(from)
                        }
                    }
                }
            }
            return result.toList()
        }
    }

    expect {
        Solution().eventualSafeNodes(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(5),
                intArrayOf(0),
                intArrayOf(5),
                intArrayOf(),
                intArrayOf(),
            )
        )
    }
}