package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
            val map = hashMapOf<Int, MutableSet<Pair<Int, Int>>>()

            roads.forEach { (from, to) ->
                val road = from.coerceAtMost(to) to from.coerceAtLeast(to)

                map.computeIfAbsent(from) { hashSetOf() }.add(road)
                map.computeIfAbsent(to) { hashSetOf() }.add(road)
            }

            var max = 0
            map.entries.toList().also {
                for (i in it.indices) {
                    for (j in i + 1 until it.size) {
                        val left = it[i].value
                        val right = it[j].value

                        max = max.coerceAtLeast((left + right).size)
                    }
                }
            }

            return max
        }
    }

    measureTimeMillis {
        Solution().maximalNetworkRank(
            1, arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}