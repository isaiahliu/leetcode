package p23xx

import util.expect

fun main() {
    class Solution {
        fun maximumGroups(grades: IntArray): Int {
            var left = 1
            var right = grades.size

            var result = 1

            while (left <= right) {
                val mid = left + (right - left) / 2

                val requireSize = mid.toLong() * (mid + 1) / 2

                if (requireSize <= grades.size) {
                    result = mid
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumGroups(
            intArrayOf(1, 2, 3, 1)
        )
    }
}