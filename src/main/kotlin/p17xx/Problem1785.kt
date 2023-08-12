package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minElements(nums: IntArray, limit: Int, goal: Int): Int {
            var sum = 0L
            nums.forEach { sum += it }

            return if (sum > goal) {
                sum - goal
            } else {
                goal - sum
            }.let {
                it / limit + if (it % limit > 0) 1 else 0
            }.toInt()
        }
    }

    measureTimeMillis {
        Solution().minElements(
            intArrayOf(), 1, 1
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
