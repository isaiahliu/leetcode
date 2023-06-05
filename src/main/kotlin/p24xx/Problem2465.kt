package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun distinctAverages(nums: IntArray): Int {
            nums.sort()

            val set = hashSetOf<Int>()

            for (i in 0 until nums.size / 2) {
                set.add(nums[i] + nums[nums.lastIndex - i])
            }

            return set.size
        }
    }

    measureTimeMillis {
        Solution().distinctAverages(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}