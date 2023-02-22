package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun majorityElement(nums: IntArray): Int {
            var t = nums[0]
            var count = 0

            for (num in nums) {
                if (count == 0) {
                    t = num
                    count++
                } else if (t == num) {
                    count++
                } else {
                    count--
                }
            }

            return t
        }
    }
    measureTimeMillis {
        Solution().majorityElement(intArrayOf()).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

