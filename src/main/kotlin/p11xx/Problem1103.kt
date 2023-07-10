package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun distributeCandies(candies: Int, num_people: Int): IntArray {
            val result = IntArray(num_people)

            var index = 0
            var count = 1
            var remaining = candies

            while (remaining > 0) {
                result[index++ % num_people] += count.coerceAtMost(remaining)

                remaining -= count++
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().distributeCandies(
            1, 3
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}