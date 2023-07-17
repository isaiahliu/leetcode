package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isEscapePossible(blocked: Array<IntArray>, source: IntArray, target: IntArray): Boolean {
            val range = 0..999999
            val blockSet = hashSetOf<Pair<Int, Int>>()
            blocked.forEach { (r, c) ->
                blockSet.add(r to c)
            }

            fun bfs(s: Pair<Int, Int>, t: Pair<Int, Int>): Boolean {
                val visited = hashSetOf(s)
                val tasks = hashSetOf(s)

                repeat(blockSet.size) {
                    if (tasks.isEmpty()) {
                        return false
                    }

                    tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                        if (t == r to c) {
                            return true
                        }

                        arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                            it.first in range && it.second in range && it !in blockSet
                        }.forEach {
                            if (visited.add(it)) {
                                tasks.add(it)
                            }
                        }
                    }
                }

                return true
            }

            return bfs(source[0] to source[1], target[0] to target[1]) && bfs(
                target[0] to target[1], source[0] to source[1]
            )
        }
    }

    measureTimeMillis {
        Solution().isEscapePossible(
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 0),
            ), intArrayOf(0, 0), intArrayOf(1, 1)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
