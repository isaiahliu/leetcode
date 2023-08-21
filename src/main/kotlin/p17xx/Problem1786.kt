package p17xx

import util.input
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun countRestrictedPaths(n: Int, edges: Array<IntArray>): Int {
            val adjacent = Array(n) {
                hashSetOf<Pair<Int, Int>>()
            }

            edges.forEach { (from, to, weight) ->
                adjacent[from - 1].add(to - 1 to weight)
                adjacent[to - 1].add(from - 1 to weight)
            }

            val distances = IntArray(n) { -1 }

            val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
            queue.add(n - 1 to 0)

            while (queue.isNotEmpty()) {
                val (node, distance) = queue.poll()

                if (distances[node] < 0) {
                    distances[node] = distance

                    adjacent[node].forEach { (to, weight) ->
                        queue.add(to to (distance + weight))
                    }
                }
            }

            val m = 1000000007L

            val dp = LongArray(n) { 0 }
            dp[n - 1] = 1L

            for (i in distances.indices.sortedBy { distances[it] }) {
                if (i == 0) {
                    break
                }

                val distance = distances[i]
                val count = dp[i]
                adjacent[i].forEach { (to, _) ->
                    if (distance < distances[to]) {
                        dp[to] += count
                        dp[to] %= m
                    }
                }
            }

            return dp[0].toInt()
        }
    }

    expect {
        Solution().countRestrictedPaths(
            20000, input.first().split("],[").map {
                it.split(",").map { it.toInt() }.toIntArray()
            }.toTypedArray()
        )
    }
}
