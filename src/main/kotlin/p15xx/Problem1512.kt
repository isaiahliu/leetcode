package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numIdenticalPairs(nums: IntArray): Int {
            return nums.toList().groupingBy { it }.eachCount().values.map {
                it * (it - 1) / 2
            }.sum()
        }
    }

    measureTimeMillis {
        Solution().numIdenticalPairs(
            intArrayOf(1)
        ).also { println(it) }
    }
}

