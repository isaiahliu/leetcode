package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTheCity(n: Int, edges: Array<IntArray>, distanceThreshold: Int): Int {
            val distances = hashMapOf<Int, MutableMap<Int, Int>>()

            edges.forEach { (from, to, distance) ->
                distances.computeIfAbsent(from) { hashMapOf() }[to] = distance
                distances.computeIfAbsent(to) { hashMapOf() }[from] = distance
            }

            return (0 until n).map {
                val visited = hashMapOf(it to 0)
                val tasks = hashMapOf(it to 0)

                while (tasks.isNotEmpty()) {
                    tasks.toMap().also { tasks.clear() }.forEach { (c, dis) ->
                        distances[c]?.forEach { (target, d) ->
                            val total = dis + d
                            if (total <= distanceThreshold && total < (visited[target] ?: Int.MAX_VALUE)) {
                                visited[target] = total
                                tasks[target] = total
                            }
                        }
                    }
                }

                it to visited.size - 1
            }.minWith(compareBy<Pair<Int, Int>> { it.second }.thenByDescending { it.first }).first
        }
    }

    measureTimeMillis {
        Solution().findTheCity(
            4, arrayOf(
                intArrayOf(0, 1, 3),
                intArrayOf(1, 2, 1),
                intArrayOf(1, 3, 4),
                intArrayOf(2, 3, 1),
            ), 4
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

