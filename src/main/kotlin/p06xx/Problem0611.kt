package p06xx

import util.expect

fun main() {
    class Solution {
        fun triangleNumber(nums: IntArray): Int {
            nums.sort()

            fun binarySearch(startIndex: Int, endIndex: Int, target: Int): Int? {
                if (startIndex > endIndex) {
                    return null
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = nums[midIndex]

                return if (midNum >= target) {
                    binarySearch(startIndex, midIndex - 1, target)
                } else {
                    binarySearch(midIndex + 1, endIndex, target) ?: midIndex
                }
            }

            var result = 0
            for (i in nums.indices) {
                for (j in i + 1 until nums.size) {
                    val sum = nums[i] + nums[j]

                    binarySearch(j + 1, nums.lastIndex, sum)?.also {
                        result += it - j
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().triangleNumber(
            intArrayOf(2, 2, 3, 4)
        )

    }
}