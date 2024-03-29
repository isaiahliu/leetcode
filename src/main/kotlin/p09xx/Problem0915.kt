package p09xx

import util.expect

fun main() {
    class Solution {
        fun partitionDisjoint(nums: IntArray): Int {
            var leftMax = nums[0]
            var rightMax = nums[0]
            var result = 0

            for (i in 1 until nums.size) {
                val num = nums[i]
                when {
                    num >= leftMax -> {
                        rightMax = rightMax.coerceAtLeast(num)
                    }

                    num < leftMax -> {
                        result = i
                        leftMax = rightMax
                    }
                }
            }

            return result + 1
        }
    }

    expect {
        Solution().partitionDisjoint(
            intArrayOf(5, 0, 3, 8, 6)
        )
    }
}