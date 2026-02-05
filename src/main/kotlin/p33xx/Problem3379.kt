package p33xx

import util.expect

fun main() {
    class Solution {
        fun constructTransformedArray(nums: IntArray): IntArray {
            return IntArray(nums.size) {
                nums[(it + nums[it]).mod(nums.size)]
            }
        }
    }

    expect {
        Solution().constructTransformedArray(
            intArrayOf()
        )
    }
}
