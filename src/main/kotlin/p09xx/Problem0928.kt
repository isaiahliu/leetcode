package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minMalwareSpread(graph: Array<IntArray>, initial: IntArray): Int {
            var minInfectCount = Int.MAX_VALUE
            var result = 0

            initial.sorted().forEach { exclude ->
                val infected = initial.toMutableSet()
                infected.remove(exclude)

                val tasks = infected.toMutableSet()

                while (tasks.isNotEmpty()) {
                    tasks.toSet().also { tasks.clear() }.forEach { n ->
                        graph[n].forEachIndexed { index, i ->
                            if (index != exclude && i == 1 && infected.add(index)) {
                                tasks.add(index)
                            }
                        }
                    }
                }

                if (infected.size < minInfectCount) {
                    minInfectCount = infected.size
                    result = exclude
                }

                println("$exclude - ${infected.size}")
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minMalwareSpread(
            arrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}