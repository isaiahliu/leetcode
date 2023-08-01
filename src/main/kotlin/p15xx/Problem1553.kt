package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minDays(n: Int): Int {
            val tasks = hashSetOf(n)
            val visited = hashSetOf(n)

            var step = 0
            while (true) {
                step++

                tasks.toSet().also { tasks.clear() }.forEach {
                    if (it == 1) {
                        return step
                    }

                    if (it % 3 == 0 && visited.add(it / 3)) {
                        tasks.add(it / 3)
                    }

                    if (it % 2 == 0 && visited.add(it / 2)) {
                        tasks.add(it / 2)
                    }

                    if (visited.add(it - 1)) {
                        tasks.add(it - 1)
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().minDays(
            946644118
        ).also { println(it) }
    }
}

