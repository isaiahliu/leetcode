package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
            val empty = hashSetOf<Pair<Int, Int>>()
            val tasks = hashSetOf(entrance[0] to entrance[1])
            maze.forEachIndexed { r, row ->
                row.forEachIndexed { c, p ->
                    when (p) {
                        '.' -> empty.add(r to c)
                    }
                }
            }

            empty.removeAll(tasks)

            var result = 0
            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach { (r, c) ->
                    if (result > 0 && (r == 0 || r == maze.lastIndex || c == 0 || c == maze[0].lastIndex)) {
                        return result
                    }

                    arrayOf(r - 1 to c, r + 1 to c, r to c - 1, r to c + 1).filter {
                        empty.remove(it)
                    }.forEach {
                        tasks.add(it)
                    }
                }

                result++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().nearestExit(
            arrayOf(), intArrayOf()
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}