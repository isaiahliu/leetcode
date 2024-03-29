package p00xx

import util.expect

fun main() {
    class Solution {
        fun maxSubArray(nums: IntArray): Int {
            var max = nums[0]
            var sum = 0

            nums.forEach {
                sum += it
                max = max.coerceAtLeast(sum)

                if (sum < 0) {
                    sum = 0
                }
            }

            return max
        }
    }

    expect {
        Solution().maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))
    }
}


