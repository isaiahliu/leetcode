package p04xx

import util.expect

fun main() {
    class Solution {
        fun numberOfArithmeticSlices(nums: IntArray): Int {
            var result = 0

            var delta = 0
            var count = 0

            for (i in 1 until nums.size) {
                (nums[i] - nums[i - 1]).also {
                    if (delta == it) {
                        count++
                    } else {
                        delta = it
                        count = 1
                    }

                    result += (count - 1).coerceAtLeast(0)
                }
            }

            return result
        }
    }
    expect {
        Solution().numberOfArithmeticSlices(
            intArrayOf(1, 2, 3, 4)
        )
    }
}


