package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minKBitFlips(nums: IntArray, k: Int): Int {
            var result = 0
            for (i in 0 until nums.size - k + 1) {
                if (nums[i] == 0) {
                    result++
                    repeat(k) {
                        nums[i + it] = 1 - nums[i + it]
                    }
                }
            }

            for (i in nums.size - k + 1 until nums.size) {
                if (nums[i] == 0) {
                    return -1
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minKBitFlips(
            intArrayOf(0, 0, 0, 1, 0, 1, 1, 0), 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
