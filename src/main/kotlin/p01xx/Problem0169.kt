package p01xx

import util.expect

fun main() {
    class Solution {
        fun majorityElement(nums: IntArray): Int {
            var t = nums[0]
            var count = 0

            for (num in nums) {
                if (count == 0) {
                    t = num
                    count++
                } else if (t == num) {
                    count++
                } else {
                    count--
                }
            }

            return t
        }
    }
    expect {
        Solution().majorityElement(intArrayOf())
    }
}

