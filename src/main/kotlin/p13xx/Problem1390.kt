package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun Int.factors(): Set<Int> {
            var p = 2

            val result = hashSetOf(1, this)
            var t = this
            while (p * p <= t) {
                if (t % p == 0) {
                    result.add(p)
                    t /= p
                    result.add(t)
                } else {

                    p++
                }
            }

            return result
        }

        fun sumFourDivisors(nums: IntArray): Int {
            var result = 0

            nums.forEach { num ->
                result += num.factors().takeIf { it.size == 4 }?.sum() ?: 0
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().sumFourDivisors(
            intArrayOf(
                8
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

