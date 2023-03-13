package p04xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minMoves2(nums: IntArray): Int {
            nums.sort()
            val mid = nums[nums.size / 2]

            return nums.map { (it - mid).absoluteValue }.sum()
        }
    }

    measureTimeMillis {
        Solution().minMoves2(intArrayOf(1, 0, 0, 8, 6)).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}