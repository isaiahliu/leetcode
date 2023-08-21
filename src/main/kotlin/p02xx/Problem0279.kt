package p02xx

import util.expect

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
                    loop@ for (tn in squares) {
                        when {
                            tn > task -> {
                                break@loop
                            }

                            tn < task -> {
                                (task - tn).takeIf { visited.add(it) }?.also {
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

    expect {
        Solution().numSquares(
            12
        )
    }
}

