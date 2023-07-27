package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minTime(n: Int, edges: Array<IntArray>, hasApple: List<Boolean>): Int {
            val children = Array(n) { hashSetOf<Int>() }
            edges.forEach { (from, to) ->
                children[from].add(to)
                children[to].add(from)
            }

            val visited = hashSetOf(0)

            fun dfs(node: Int): Int? {
                var result = 0

                children[node].forEach {
                    if (visited.add(it)) {
                        dfs(it)?.also {
                            result += it + 2
                        }
                    }
                }

                return if (result > 0) {
                    result
                } else if (hasApple[node]) {
                    0
                } else {
                    null
                }
            }

            return dfs(0) ?: 0
        }
    }

    measureTimeMillis {
        Solution().minTime(
            4, arrayOf(
                intArrayOf(0, 2),
                intArrayOf(0, 3),
                intArrayOf(1, 2),
            ), listOf(false, true, false, false)
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

