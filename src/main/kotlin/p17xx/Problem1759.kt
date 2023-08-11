package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countHomogenous(s: String): Int {
            var count = 0
            var pre = s[0]

            var result = 0L
            val m = 1000000007

            s.forEach {
                if (it == pre) {
                    count++
                } else {
                    count = 1
                    pre = it
                }

                result += count
                result %= m
            }

            return result.toInt()
        }
    }

    measureTimeMillis {
        Solution().countHomogenous(
            "abbcccaa"
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
