package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun xorGame(nums: IntArray): Boolean {
            return nums.size % 2 == 0 || nums.fold(0) { a, b -> a xor b } == 0
        }
    }

    measureTimeMillis {
        Solution().xorGame(
            intArrayOf(1, 1, 2)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}