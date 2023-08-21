package p06xx

import util.expect

fun main() {
    class Solution {
        fun findLengthOfLCIS(nums: IntArray): Int {
            var pre = nums[0]
            var size = 1
            var maxSize = 1

            nums.forEach {
                if (it > pre) {
                    size++

                    maxSize = maxSize.coerceAtLeast(size)
                } else {
                    size = 1
                }

                pre = it
            }

            return maxSize
        }
    }

    expect {
        Solution().findLengthOfLCIS(
            intArrayOf(
                1, 3, 5, 4, 7
            )
        )
    }
}