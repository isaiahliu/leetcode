package p17xx

import util.expect

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

    expect {
        Solution().minElements(
            intArrayOf(), 1, 1
        )
    }
}
