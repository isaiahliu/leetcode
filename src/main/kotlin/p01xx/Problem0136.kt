package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun singleNumber(nums: IntArray): Int {
            return nums.reduce { a, b -> a xor b }
        }
    }

    measureTimeMillis {
        Solution().singleNumber(
            intArrayOf(1, 2, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

