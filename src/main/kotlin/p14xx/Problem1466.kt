package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minReorder(n: Int, connections: Array<IntArray>): Int {
            val reachable = Array(n) {
                hashMapOf<Int, Int>()
            }

            connections.forEach { (from, to) ->
                reachable[from][to] = 1
                reachable[to][from] = 0
            }

            val visited = hashSetOf(0)
            val tasks = hashSetOf(0)

            var result = 0

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach {
                    reachable[it].forEach { (target, cost) ->
                        if (visited.add(target)) {
                            result += cost
                            tasks.add(target)
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minReorder(
            5, arrayOf()
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

