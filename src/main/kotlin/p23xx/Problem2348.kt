package p23xx

import util.expect

fun main() {
    class Solution {
        fun zeroFilledSubarray(nums: IntArray): Long {
            var result = 0L

            var count = 0
            (nums + 1).forEach {
                if (it == 0) {
                    count++
                } else if (count > 0) {
                    result += count
                    result += count * (count + 1L) / 2
                    count = 0
                }
            }

            return result
        }
    }

    expect {
        Solution().zeroFilledSubarray(
            intArrayOf()
        )
    }
}