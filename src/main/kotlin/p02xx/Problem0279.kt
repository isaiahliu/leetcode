package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numSquares(n: Int): Int {
            val squares = IntArray(100) {
                (it + 1) * (it + 1)
            }

            val visited = hashSetOf(n)
            val tasks = hashSetOf(n)

            var step = 0
            while (tasks.isNotEmpty()) {
                step++
                for (task in tasks.toList().also { tasks.clear() }) {
                    loop@ for (n in squares) {
                        when {
                            n > task -> {
                                break@loop
                            }

                            n < task -> {
                                (task - n).takeIf { visited.add(it) }?.also {
                                    tasks.add(it)
                                }
                            }

                            else -> {
                                return step
                            }
                        }
                    }
                }
            }

            return 0
        }
    }

    measureTimeMillis {
        Solution().numSquares(
            12
        ).also { println(it) }
    }
}

