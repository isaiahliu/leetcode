package p12xx

import util.expect

fun main() {
    class Solution {
        fun numberOfSubarrays(nums: IntArray, k: Int): Int {
            val evenLeftCounts = IntArray(nums.size)
            val evenRightCounts = IntArray(nums.size)

            val oddIndices = arrayListOf<Int>()

            var count = 0
            for (i in nums.indices) {
                if (nums[i] % 2 == 0) {
                    count++
                } else {
                    evenLeftCounts[i] = count
                    count = 0

                    oddIndices.add(i)
                }
            }

            count = 0
            for (i in nums.lastIndex downTo 0) {
                if (nums[i] % 2 == 0) {
                    count++
                } else {
                    evenRightCounts[i] = count
                    count = 0
                }
            }

            var result = 0
            for (i in 0 until oddIndices.size - k + 1) {
                result += (evenLeftCounts[oddIndices[i]] + 1) * (evenRightCounts[oddIndices[i + k - 1]] + 1)
            }

            return result
        }
    }

    expect {
        Solution().numberOfSubarrays(
            intArrayOf(), 1
        )
    }
}

