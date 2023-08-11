package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minTrioDegree(n: Int, edges: Array<IntArray>): Int {
            val adjacent = Array(n) { hashSetOf<Int>() }
            val degress = IntArray(n)

            edges.forEach { (from, to) ->
                adjacent[(from - 1).coerceAtMost(to - 1)].add((from - 1).coerceAtLeast(to - 1))
                degress[from - 1]++
                degress[to - 1]++
            }

            var result = Int.MAX_VALUE
            for (i in 0 until n) {
                adjacent[i].forEach { j ->
                    adjacent[j].intersect(adjacent[i]).forEach {
                        result = result.coerceAtMost(degress[i] + degress[j] + degress[it] - 6)
                    }
                }
            }

            return result.takeIf { it < Int.MAX_VALUE } ?: -1
        }
    }

    measureTimeMillis {
        Solution().minTrioDegree(
            6, arrayOf(
                intArrayOf(6, 5),
                intArrayOf(4, 3),
                intArrayOf(5, 1),
                intArrayOf(1, 4),
                intArrayOf(2, 3),
                intArrayOf(4, 5),
                intArrayOf(2, 6),
                intArrayOf(1, 3)
            )
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
