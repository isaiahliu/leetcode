package p24xx

import util.expect

fun main() {
    class Solution {
        fun countSubarrays(nums: IntArray, minK: Int, maxK: Int): Long {
            var result = 0L
            var startIndex = nums.size
            var lastMinIndex = -1
            var lastMaxIndex = -1
            nums.forEachIndexed { index, num ->
                if (num !in minK..maxK) {
                    startIndex = nums.size
                    lastMinIndex = -1
                    lastMaxIndex = -1
                } else {
                    startIndex = index.coerceAtMost(startIndex)

                    if (num == minK) {
                        lastMinIndex = index
                    }

                    if (num == maxK) {
                        lastMaxIndex = index
                    }

                    result += (lastMinIndex.coerceAtMost(lastMaxIndex) - startIndex + 1).coerceAtLeast(0)
                }
            }

            return result
        }
    }

    expect {
        Solution().countSubarrays(
            intArrayOf(1, 3, 5, 2, 7, 5), 1, 2
        )
    }
}