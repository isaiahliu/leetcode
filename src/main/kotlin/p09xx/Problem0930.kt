package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numSubarraysWithSum(nums: IntArray, goal: Int): Int {
            val sumMap = hashMapOf(0 to 1)

            var result = 0
            var sum = 0
            nums.forEach { num ->
                sum += num

                sumMap[sum - goal]?.also { result += it }

                sumMap[sum] = (sumMap[sum] ?: 0) + 1
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().numSubarraysWithSum(
            intArrayOf(1, 0, 1), 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}