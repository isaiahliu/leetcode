package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun openLock(deadends: Array<String>, target: String): Int {
            val init = (0 to 0) to (0 to 0)

            val deads = deadends.map {
                it.toCharArray().let { (a, b, c, d) ->
                    (a - '0' to b - '0') to (c - '0' to d - '0')
                }
            }

            if (init in deads) {
                return -1
            }
            val t = target.toCharArray().let { (a, b, c, d) ->
                (a - '0' to b - '0') to (c - '0' to d - '0')
            }

            val visited = hashSetOf(init)

            val tasks = hashSetOf(init)

            var result = 0

            while (tasks.isNotEmpty()) {
                tasks.toSet().also { tasks.clear() }.forEach {
                    if (it == t) {
                        return result
                    }
                    val (a, b) = it.first
                    val (c, d) = it.second

                    arrayOf(
                        ((a + 1) % 10 to b) to (c to d),
                        ((a + 9) % 10 to b) to (c to d),
                        (a to (b + 1) % 10) to (c to d),
                        (a to (b + 9) % 10) to (c to d),
                        (a to b) to ((c + 1) % 10 to d),
                        (a to b) to ((c + 9) % 10 to d),
                        (a to b) to (c to (d + 1) % 10),
                        (a to b) to (c to (d + 9) % 10)
                    ).forEach {
                        if (visited.add(it) && it !in deads) {
                            tasks.add(it)
                        }
                    }
                }
                result++
            }

            return -1
        }
    }

    measureTimeMillis {
        Solution().openLock(
            arrayOf(
                "0201", "0101", "0102", "1212", "2002"
            ), "0202"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}