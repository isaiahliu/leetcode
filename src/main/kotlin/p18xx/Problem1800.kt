package p18xx

import util.expect

fun main() {
    class Solution {
        fun maxAscendingSum(nums: IntArray): Int {
            var result = 0
            var sum = 0
            var pre = 0

            nums.forEach {
                if (it <= pre) {
                    sum = 0
                }

                sum += it
                result = result.coerceAtLeast(sum)
                pre = it
            }

            return result
        }
    }

    expect {
        Solution().maxAscendingSum(
            intArrayOf(1, 4, 10, 3, 1)
        )
    }
}
