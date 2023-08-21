package p23xx

import util.expect

fun main() {
    class Solution {
        fun answerQueries(nums: IntArray, queries: IntArray): IntArray {
            nums.sort()

            for (i in 1 until nums.size) {
                nums[i] += nums[i - 1]
            }

            fun binarySearch(startIndex: Int, endIndex: Int, target: Int): Int {
                if (startIndex > endIndex) {
                    return -1
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = nums[midIndex]

                return when {
                    midNum < target -> {
                        binarySearch(midIndex + 1, endIndex, target).coerceAtLeast(midIndex)
                    }

                    midNum > target -> {
                        binarySearch(startIndex, midIndex - 1, target)
                    }

                    else -> {
                        midIndex
                    }
                }
            }

            return IntArray(queries.size) {
                binarySearch(0, nums.lastIndex, queries[it]) + 1
            }
        }
    }

    expect {
        Solution().answerQueries(
            intArrayOf(), intArrayOf()
        )
    }
}