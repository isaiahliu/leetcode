package p19xx

import util.expect

fun main() {
    class Solution {
        fun buildArray(nums: IntArray): IntArray {
            return IntArray(nums.size) {
                nums[nums[it]]
            }
        }
    }

    expect {
        Solution().buildArray(
            intArrayOf(-1, 0, 0, 1, 2)
        )
    }
}