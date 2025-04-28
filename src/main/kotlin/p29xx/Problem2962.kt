package p29xx

import util.expect

fun main() {
    class Solution {
        fun countSubarrays(nums: IntArray, k: Int): Long {
            val max = nums.max()

            var left = -1
            var right = -1

            var maxCount = 0

            var result = 0L

            while (true) {
                nums.getOrNull(left++)?.takeIf { it == max }?.also { maxCount-- }

                while (maxCount < k && right < nums.lastIndex) {
                    right++

                    if (nums[right] == max) {
                        maxCount++
                    }
                }

                if (maxCount == k) {
                    result += nums.size - right
                } else {
                    break
                }
            }

            return result
        }
    }
    expect {
        Solution().countSubarrays(
            intArrayOf(7, 12, 9, 8, 9, 15), 4
        )
    }
}
