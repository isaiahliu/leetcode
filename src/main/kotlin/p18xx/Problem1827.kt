package p18xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(nums: IntArray): Int {
            var min = nums[0]
            var result = 0
            nums.forEach {
                if (min > it) {
                    result += min - it
                }

                min = min.coerceAtLeast(it) + 1
            }

            return result
        }
    }

    expect {
        Solution().minOperations(
            intArrayOf(1, 1, 1)
        )

    }
}
