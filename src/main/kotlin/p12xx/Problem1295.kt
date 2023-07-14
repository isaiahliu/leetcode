package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findNumbers(nums: IntArray): Int {
            return nums.count { it.toString().length % 2 == 0 }
        }
    }

    measureTimeMillis {
        Solution().findNumbers(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
