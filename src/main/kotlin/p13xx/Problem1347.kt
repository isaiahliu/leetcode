package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minSteps(s: String, t: String): Int {
            val counts = t.groupingBy { it }.eachCount()
            var result = 0
            s.groupingBy { it }.eachCount().forEach { (char, count) ->
                val target = counts[char] ?: 0

                if (count > target) {
                    result += count - target
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minSteps(
            "", ""
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

