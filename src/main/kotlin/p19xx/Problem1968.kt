package p19xx

import util.expect

fun main() {
    class Solution {
        fun rearrangeArray(nums: IntArray): IntArray {
            nums.sort()

            return IntArray(nums.size) {
                if (it % 2 == 0) {
                    nums[it / 2]
                } else {
                    nums[nums.lastIndex - it / 2]
                }
            }
        }
    }

    expect {
        Solution().rearrangeArray(
            intArrayOf()
        )
    }
}