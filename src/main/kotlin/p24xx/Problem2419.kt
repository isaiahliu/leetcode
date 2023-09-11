package p24xx

import util.expect

fun main() {
    class Solution {
        fun longestSubarray(nums: IntArray): Int {
            var result = 0
            var max = nums[0]
            var size = 0
            var pre = nums[0]
            nums.forEach {
                if (it != pre) {
                    if (it > max) {
                        max = it
                        result = 0
                    }
                    size = 0
                }

                if (it == max) {
                    size++
                    result = result.coerceAtLeast(size)
                }

                pre = it
            }

            return result
        }
    }

    expect {
        Solution().longestSubarray(
            intArrayOf()
        )
    }
}