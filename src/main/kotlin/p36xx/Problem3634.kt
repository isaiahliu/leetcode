package p36xx

import util.expect

fun main() {
    class Solution {
        fun minRemoval(nums: IntArray, k: Int): Int {
            nums.sort()

            var result = nums.size
            var left = 0
            var right = 0

            while (right < nums.size) {
                while (right < nums.size && nums[left] * k.toLong() >= nums[right]) {
                    right++
                }

                result = minOf(result, nums.size - right + left)

                left++
            }

            return result
        }
    }

    expect {
        Solution().minRemoval(
            intArrayOf(), 1
        )
    }
}
