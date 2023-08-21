package p17xx

import util.expect

fun main() {
    class Solution {
        fun maxAbsoluteSum(nums: IntArray): Int {
            var result = 0

            var neg = 0
            var pos = 0

            nums.forEach {
                neg = (neg + it).coerceAtMost(0)
                pos = (pos + it).coerceAtLeast(0)

                result = result.coerceAtLeast(pos).coerceAtLeast(-neg)
            }

            return result
        }
    }

    expect {
        Solution().maxAbsoluteSum(
            intArrayOf()
        )
    }
}

