package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countOdds(low: Int, high: Int): Int {
            return (high - low + 1) / 2 + (low and high and 1)
        }
    }

    measureTimeMillis {
        Solution().countOdds(
            8, 10
        ).also { println(it) }
    }
}

