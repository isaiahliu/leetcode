package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun subarraySum(nums: IntArray, k: Int): Int {
            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }

            val map = hashMapOf(0 to 1)
            var result = 0

            nums.forEach {
                map[it - k]?.also { result += it }

                map[it] = (map[it] ?: 0) + 1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().subarraySum(
            intArrayOf(1, 1, 1), 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}