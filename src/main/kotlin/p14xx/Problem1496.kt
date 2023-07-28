package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun isPathCrossing(path: String): Boolean {
            var r = 0
            var c = 0

            val visited = hashSetOf(r to c)

            path.forEach {
                when (it) {
                    'N' -> r--
                    'S' -> r++
                    'E' -> c++
                    'W' -> c--
                }
                if (!visited.add(r to c)) {
                    return true
                }
            }
            return false
        }
    }

    measureTimeMillis {
        Solution().isPathCrossing(
            "NES"
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

