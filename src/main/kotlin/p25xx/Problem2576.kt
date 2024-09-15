package p25xx

import util.expect

fun main() {
    class Solution {
        fun maxNumOfMarkedIndices(nums: IntArray): Int {
            nums.sort()

            var result = 0

            var i = 0
            var j = nums.size / 2 + nums.size % 2

            while (j < nums.size) {
                if (nums[i] * 2 <= nums[j]) {
                    result += 2
                    i++
                }
                j++
            }

            return result
        }
    }

    expect {
        Solution().maxNumOfMarkedIndices(
            intArrayOf()
        )
    }
}