package p02xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun missingNumber(nums: IntArray): Int {
            val target = nums.size.let { it * (it + 1) / 2 }

            return target - nums.sum()
        }
    }

    measureTimeMillis {
        Solution().missingNumber(
            intArrayOf()
        ).also { println(it) }
    }
}

