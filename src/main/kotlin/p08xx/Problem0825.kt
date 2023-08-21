package p08xx

import util.expect

fun main() {
    class Solution {
        fun numFriendRequests(ages: IntArray): Int {
            ages.sort()

            fun binarySearch(startIndex: Int, endIndex: Int, target: Int, rightMost: Boolean): Int {
                val midIndex = (startIndex + endIndex) / 2
                val midNum = ages[midIndex]
                return when {
                    startIndex > endIndex && rightMost -> -1
                    startIndex > endIndex && !rightMost -> ages.size
                    midNum <= target && rightMost -> midIndex.coerceAtLeast(
                        binarySearch(midIndex + 1, endIndex, target, true)
                    )

                    midNum >= target && !rightMost -> midIndex.coerceAtMost(
                        binarySearch(startIndex, midIndex - 1, target, false)
                    )

                    midNum < target -> binarySearch(midIndex + 1, endIndex, target, false)
                    else -> binarySearch(startIndex, midIndex - 1, target, true)
                }
            }

            var result = 0
            ages.forEach { age ->
                val min = age / 2 + 7
                val max = age + 1

                val left = binarySearch(0, ages.lastIndex, min, true)
                val right = binarySearch(0, ages.lastIndex, max, false)

                result += (right - left - 2).coerceAtLeast(0)

            }

            return result
        }
    }

    expect {
        Solution().numFriendRequests(
            intArrayOf(16, 17, 18)
        )
    }
}