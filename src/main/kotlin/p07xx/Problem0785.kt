package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isBipartite(graph: Array<IntArray>): Boolean {
            val groups = hashMapOf(0 to 0)

            val remainings = graph.indices.toMutableSet()
            remainings.remove(0)

            val queue = hashSetOf(0)

            while (true) {
                val num = queue.firstOrNull()?.also { queue.remove(it) } ?: remainings.firstOrNull()
                    ?.also { remainings.remove(it) } ?: break

                val group = groups.computeIfAbsent(num) { 0 }

                graph[num].forEach { target ->
                    if (groups[target]?.takeIf { it == group } != null) {
                        return false
                    }

                    if (remainings.remove(target)) {
                        queue.add(target)
                    }

                    groups[target] = 1 - group
                }
            }

            return true
        }
    }

    measureTimeMillis {
        Solution().isBipartite(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(0, 2),
                intArrayOf(0, 1, 3),
                intArrayOf(0, 2),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}