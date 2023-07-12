package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun criticalConnections(n: Int, connections: List<List<Int>>): List<List<Int>> {
            val reachable = Array(n) { hashSetOf<Int>() }

            for ((from, to) in connections) {
                reachable[from].add(to)
                reachable[to].add(from)
            }

            val result = arrayListOf<List<Int>>()

            val time = IntArray(n)
            val low = IntArray(n)
            var num = 0

            fun dfs(to: Int, from: Int) {
                time[to] = ++num
                low[to] = time[to]

                for (next in reachable[to]) {
                    if (time[next] == 0) {
                        dfs(next, to)

                        low[to] = low[to].coerceAtMost(low[next])

                        if (low[next] > time[to]) {
                            result.add(listOf(to, next))
                        }
                    } else if (time[next] < time[to] && next != from) {
                        low[to] = low[to].coerceAtMost(time[next])
                    }
                }
            }
            dfs(0, 0)

            return result
        }
    }

    measureTimeMillis {
        Solution().criticalConnections(
            4, listOf(
                listOf(0, 1),
                listOf(0, 2),
                listOf(1, 2),
                listOf(1, 3),
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}