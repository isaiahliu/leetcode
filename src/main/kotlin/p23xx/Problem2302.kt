package p23xx

import util.expect

fun main() {
    class Solution {
        fun countSubarrays(nums: IntArray, k: Long): Long {
            var s = 0L
            val sums = LongArray(nums.size) {
                s += nums[it]

                s
            }

            var result = 0L
            for (startIndex in nums.indices) {
                val preSum = sums.getOrNull(startIndex - 1) ?: 0L

                var l = startIndex
                var r = nums.lastIndex
                var endIndex = startIndex - 1

                while (l <= r) {
                    val mid = (l + r) / 2

                    val count = mid - startIndex + 1
                    val sum = sums[mid] - preSum

                    if (count * sum < k) {
                        endIndex = mid
                        l = mid + 1
                    } else {
                        r = mid - 1
                    }
                }

                result += endIndex - startIndex + 1
            }

            return result
        }
    }

    expect {
        Solution().countSubarrays(
            intArrayOf(), 1L
        )
    }
}