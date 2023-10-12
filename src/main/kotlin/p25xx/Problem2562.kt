package p25xx

import util.expect

fun main() {
    class Solution {
        fun findTheArrayConcVal(nums: IntArray): Long {
            var result = 0L
            (0 until nums.size / 2).forEach {
                result += "${nums[it]}${nums[nums.lastIndex - it]}".toLong()
            }

            if (nums.size % 2 == 1) {
                result += nums[nums.size / 2]
            }

            return result
        }
    }

    expect {
        Solution().findTheArrayConcVal(
            intArrayOf(5, 2, 9, 8, 4)
        )
    }
}