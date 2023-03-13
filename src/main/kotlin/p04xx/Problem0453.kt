package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minMoves(nums: IntArray): Int {
            val min = nums.min()

            return nums.map { it - min }.sum()
        }
    }

    measureTimeMillis {
        Solution().minMoves(intArrayOf()).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}