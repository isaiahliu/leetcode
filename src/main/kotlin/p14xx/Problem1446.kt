package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxPower(s: String): Int {
            var result = 1
            var count = 0
            var pre = s[0]

            s.forEach {
                if (it == pre) {
                    result = result.coerceAtLeast(++count)
                } else {
                    pre = it
                    count = 1
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxPower(
            ""
        ).also { println("${it} should be ${it}") }

    }.also { println("Time cost: ${it}ms") }
}

