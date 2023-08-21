package p24xx

import util.expect

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

    expect {
        Solution().distinctAverages(
            intArrayOf()
        )
    }
}