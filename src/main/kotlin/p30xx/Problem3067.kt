package p30xx

import util.expect

fun main() {
    class Solution {
        fun countPairsOfConnectableServers(edges: Array<IntArray>, signalSpeed: Int): IntArray {
            val adjacent = Array(edges.size + 1) {
                hashMapOf<Int, Int>()
            }

            edges.forEach { (from, to, weight) ->
                adjacent[from][to] = weight
                adjacent[to][from] = weight
            }

            return IntArray(edges.size + 1) { pos ->
                val visited = hashSetOf(pos)
                val routes = hashMapOf<Int, Int>()
                var count = 0

                fun dfs(routeStart: Int, from: Int, weight: Int) {
                    if (weight % signalSpeed == 0) {
                        count++
                        routes[routeStart] = (routes[routeStart] ?: 0) + 1
                    }

                    adjacent[from].forEach { (to, w) ->
                        if (visited.add(to)) {
                            dfs(routeStart, to, weight + w)
                        }
                    }
                }

                adjacent[pos].forEach { (to, weight) ->
                    visited += to
                    dfs(to, to, weight)
                }

                routes.values.sumOf { it * (count - it) } / 2
            }
        }
    }

    expect {
        Solution().countPairsOfConnectableServers(
            arrayOf(), 1
        )
    }
}
