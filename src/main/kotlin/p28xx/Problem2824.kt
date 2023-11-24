package p28xx

import util.expect

fun main() {
    class Solution {
        fun countPairs(nums: List<Int>, target: Int): Int {
            val sorted = nums.sorted()

            var left = 0
            var right = sorted.lastIndex
            var result = 0
            while (left < right) {
                if (sorted[left] + sorted[right] >= target) {
                    right--
                } else {
                    result += right - left
                    left++
                    continue
                }
            }
            return result
        }
    }

    expect {
        Solution().countPairs(
            listOf(-6, 2, 5, -2, -7, -1, 3), -2
        )
    }
}
