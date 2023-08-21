package p15xx

import util.expect

fun main() {
    class Solution {
        fun maxProbability(
            n: Int,
            edges: Array<IntArray>,
            succProb: DoubleArray,
            start_node: Int,
            end_node: Int
        ): Double {
            val neighbors = hashMapOf<Int, MutableMap<Int, Double>>()

            edges.forEachIndexed { index, (from, to) ->
                neighbors.computeIfAbsent(from) { hashMapOf() }[to] = succProb[index]
                neighbors.computeIfAbsent(to) { hashMapOf() }[from] = succProb[index]
            }

            val possibles = DoubleArray(n) { 0.0 }

            possibles[start_node] = 1.0
            val tasks = hashSetOf(start_node)

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { from ->
                    val basePossible = possibles[from]

                    neighbors[from]?.forEach { (to, possible) ->
                        if (possibles[to] < basePossible * possible) {
                            possibles[to] = basePossible * possible
                            tasks.add(to)
                        }
                    }
                }
            }

            return possibles[end_node]
        }
    }

    expect {
        Solution().maxProbability(
            5, arrayOf(intArrayOf(1)), doubleArrayOf(), 1, 1
        )
    }
}

