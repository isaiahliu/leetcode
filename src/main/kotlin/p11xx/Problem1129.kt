package p11xx

import util.expect

fun main() {
    class Solution {
        fun shortestAlternatingPaths(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
            val routes = Array(2) { Array(n) { hashSetOf<Int>() } }

            redEdges.forEach { (from, to) -> routes[0][from].add(to) }
            blueEdges.forEach { (from, to) -> routes[1][from].add(to) }

            val path = Array(n) {
                intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE)
            }

            path[0][0] = 0
            path[0][1] = 0

            val tasks = hashSetOf(0 to 0, 0 to 1)

            var distance = 0

            while (tasks.isNotEmpty()) {
                distance++
                tasks.toSet().also { tasks.clear() }.forEach { (pos, type) ->
                    routes[type][pos].forEach { target ->
                        if (path[target][1 - type] > distance) {
                            tasks.add(target to 1 - type)
                            path[target][1 - type] = distance
                        }
                    }
                }
            }

            return path.map { it[0].coerceAtMost(it[1]).takeIf { it < Int.MAX_VALUE } ?: -1 }.toIntArray()
        }
    }

    expect {
        Solution().shortestAlternatingPaths(
            1, arrayOf(), arrayOf()
        )

    }
}