package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun shortestPathLength(graph: Array<IntArray>): Int {
            fun Int.forEachBit(consumer: (Int) -> Unit) {
                var t = this

                var index = 0
                while (t > 0) {
                    if (t % 2 == 1) {
                        consumer(index)
                    }

                    t /= 2
                    index++
                }
            }

            val target = (1 shl graph.size) - 1
            var result = 0

            val visited = graph.indices.map { (1 shl it) to it }.toMutableSet()
            val tasks = visited.toMutableSet()

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (status, point) ->
                    if (status == target) {
                        return result
                    }

                    graph[point].forEach {
                        val pos = 1 shl it

                        var newStatus = status
                        if (pos and status == 0) {
                            newStatus += pos
                        }

                        if (visited.add(newStatus to it)) {
                            tasks.add(newStatus to it)
                        }
                    }
                }

                result++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().shortestPathLength(
//            arrayOf(
//                intArrayOf(1, 2),
//                intArrayOf(0, 2),
//                intArrayOf(0, 1),
//            )

            arrayOf(
                intArrayOf(1),
                intArrayOf(0, 2, 6),
                intArrayOf(1, 3),
                intArrayOf(2),
                intArrayOf(5),
                intArrayOf(4, 6),
                intArrayOf(1, 5, 7),
                intArrayOf(6),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}