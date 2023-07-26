package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
            val max = candies.max()

            return candies.map {
                it + extraCandies >= max
            }
        }
    }

    measureTimeMillis {
        Solution().kidsWithCandies(
            intArrayOf(), 19
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

