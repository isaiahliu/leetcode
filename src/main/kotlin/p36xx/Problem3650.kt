package p36xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minCost(n: Int, edges: Array<IntArray>): Int {
            val adjacent = Array(n) { hashMapOf<Int, Int>() }

            edges.forEach { (from, to, cost) ->
                adjacent[from][to] = minOf(adjacent[from][to] ?: Int.MAX_VALUE, cost)
                adjacent[to][from] = minOf(adjacent[to][from] ?: Int.MAX_VALUE, cost * 2)
            }

            val visited = hashSetOf<Int>()

            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
            queue.add(0 to 0)

            while (queue.isNotEmpty()) {
                val (pos, cost) = queue.poll()

                when {
                    pos == n - 1 -> {
                        return cost
                    }

                    visited.add(pos) -> {
                        adjacent[pos].forEach { (next, c) ->
                            queue.add(next to cost + c)
                        }
                    }
                }
            }

            return -1
        }
    }

    expect {
        Solution().minCost(
            1, arrayOf()
        )
    }
}
