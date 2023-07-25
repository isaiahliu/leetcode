package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countLargestGroup(n: Int): Int {
            return (1..n).map {
                var t = it
                var result = 0
                while (t > 0) {
                    result += t % 10
                    t /= 10
                }

                result
            }.groupingBy { it }.eachCount().values.let {
                val max = it.max()

                it.count { it == max }
            }
        }
    }

    measureTimeMillis {
        Solution().countLargestGroup(
            10
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

