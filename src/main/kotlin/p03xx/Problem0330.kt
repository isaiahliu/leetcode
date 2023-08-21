package p03xx

import util.expect

fun main() {
    class Solution {
        fun minPatches(nums: IntArray, n: Int): Int {
            if (n == 1) {
                return if (1 in nums) 0 else 1
            }

            var result = 0

            var currentMax = 0L
            var index = 0
            while (currentMax < n) {
                val currentNum = nums.getOrElse(index) { Int.MAX_VALUE }.toLong()

                when {
                    currentNum > currentMax + 1 -> {
                        result++
                        currentMax = currentMax * 2 + 1
                    }

                    else -> {
                        currentMax += currentNum
                        index++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minPatches(
            intArrayOf(1, 2, 2), 5
        )
    }
}

