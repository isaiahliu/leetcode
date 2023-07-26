package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minSubsequence(nums: IntArray): List<Int> {
            val sum = nums.sum()
            nums.sortDescending()

            var t = 0
            val result = arrayListOf<Int>()

            nums.forEach {
                t += it
                result.add(it)

                if (t * 2 > sum) {
                    return result
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minSubsequence(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

