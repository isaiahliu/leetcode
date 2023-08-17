package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getLucky(s: String, k: Int): Int {
            var sum = s.map {
                when (val num = it - 'a' + 1) {
                    in 1..9 -> num
                    in 10..19 -> num - 9
                    else -> num - 18
                }
            }.sum()

            repeat(k - 1) {
                var newSum = 0

                while (sum > 0) {
                    newSum += sum % 10
                    sum /= 10
                }
                sum = newSum
            }

            return sum
        }
    }

    measureTimeMillis {
        Solution().getLucky(
            "", 1
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}