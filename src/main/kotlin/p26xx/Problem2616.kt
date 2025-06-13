package p26xx

import util.expect

fun main() {
    class Solution {
        fun minimizeMax(nums: IntArray, p: Int): Int {
            nums.sort()

            var result = Int.MAX_VALUE

            var left = 0
            var right = Int.MAX_VALUE

            while (left <= right) {
                var mid = left + (right - left) / 2

                var index = 0
                var count = 0
                var prev: Int? = null
                while (index < nums.size && count < p) {
                    val num = nums[index++]

                    prev?.takeIf { num - it <= mid }?.also {
                        count++
                        prev = null
                    } ?: run {
                        prev = num
                    }
                }

                if (count == p) {
                    result = mid
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().minimizeMax(
            intArrayOf(1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1), 1
        )
    }
}