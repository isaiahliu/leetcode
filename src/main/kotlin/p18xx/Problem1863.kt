package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun subsetXORSum(nums: IntArray): Int {
            return nums.fold(0) { a, b -> a or b } shl (nums.size - 1)
        }
    }

    measureTimeMillis {
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2)
        ).also { println("${it} should be $it") }

        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3)
        ).also { println("${it} should be $it") }

        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10)
        ).also { println("${it} should be $it") }
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11)
        ).also { println("${it} should be $it") }
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12)
        ).also { println("${it} should be $it") }
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12, 13)
        ).also { println("${it} should be $it") }
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12, 13, 14)
        ).also { println("${it} should be $it") }
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12, 13, 14, 15)
        ).also { println("${it} should be $it") }
        Solution().subsetXORSum(
            intArrayOf(1, 2, 3, 2, 3, 10, 11, 12, 13, 14, 15, 15)
        ).also { println("${it} should be $it") }
        Solution().subsetXORSum(
            IntArray(16) { 2 }
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
