package p21xx

import util.expect

fun main() {
    class Solution {
        fun countElements(nums: IntArray): Int {
            nums.sort()

            val min = nums[0]
            val max = nums.last()

            return if (min == max) {
                0
            } else {
                var left = 0
                var right = nums.lastIndex

                while (nums[left] == min) {
                    left++
                }

                while (nums[right] == max) {
                    right--
                }

                right - left + 1
            }
        }
    }

    expect {
        Solution().countElements(
            intArrayOf()
        )
    }
}