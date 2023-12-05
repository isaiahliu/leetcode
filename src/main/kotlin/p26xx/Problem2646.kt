package p26xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumTotalPrice(n: Int, edges: Array<IntArray>, price: IntArray, trips: Array<IntArray>): Int {
            val adjacent = Array(n) { hashSetOf<Int>() }
            val degrees = IntArray(n)

            edges.forEach { (from, to) ->
                adjacent[from].add(to)
                adjacent[to].add(from)
                degrees[from]++
                degrees[to]++
            }

            fun findRoute(from: Int, to: Int): Set<Int> {
                val result = hashSetOf<Int>()

                val times = IntArray(n) { -1 }

                val queue = LinkedList<Pair<Int, Int>>()
                queue.add(from to 0)

                while (times[to] < 0) {
                    val (node, time) = queue.poll()

                    times[node] = time

                    adjacent[node].forEach {
                        if (times[it] < 0) {
                            queue.add(it to time + 1)
                        }
                    }
                }

                var current = to
                var toDegree = times[to]
                result.add(current)

                while (toDegree > 0) {
                    toDegree--
                    current = adjacent[current].first { times[it] == toDegree }
                    result.add(current)
                }

                return result
            }

            val visited = IntArray(n)
            trips.forEach { (from, to) ->
                findRoute(from, to).forEach {
                    visited[it]++
                }
            }

            val queue = LinkedList<Int>()
            degrees.forEachIndexed { index, i ->
                if (i <= 1) {
                    queue.add(index)
                }
            }

            val dp = Array(n) {
                //cut / uncut
                intArrayOf(0, 0)
            }
            var result = 0
            while (queue.isNotEmpty()) {
                val node = queue.poll()

                val count = visited[node]
                val p = price[node]

                dp[node][0] = count * (p / 2)
                dp[node][1] = count * p

                adjacent[node].forEach {
                    when (degrees[it]) {
                        0 -> {
                            dp[node][0] += dp[it][1]
                            dp[node][1] += dp[it].min()
                        }

                        1 -> {
                            queue.add(it)
                        }

                        2 -> {
                            degrees[it]--
                            queue.add(it)
                        }

                        else -> {
                            degrees[it]--
                        }
                    }
                }

                result = dp[node].min()

                if (degrees[node] > 0) {
                    degrees[node]--
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumTotalPrice(
            1, arrayOf(),
            intArrayOf(2),
            arrayOf(intArrayOf(0, 0))
        )
    }
}