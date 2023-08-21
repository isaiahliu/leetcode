package p06xx

import util.expect

fun main() {
    class Solution {
        fun smallestRange(nums: List<List<Int>>): IntArray {
            val indices = Array(nums.size) { 0 }
            val lastNums = Array(nums.size) { -9999999 }

            val range = nums.indices
            var minSize = 1000000
            val result = intArrayOf(0, 0)
            while (range.any { indices[it] < nums[it].size }) {
                val min = range.mapNotNull { nums[it].getOrNull(indices[it]) }.min()

                range.forEach {
                    if (nums[it].getOrNull(indices[it]) == min) {
                        lastNums[it] = min
                        indices[it]++

                        val l = lastNums.min()

                        if (min - l < minSize) {
                            minSize = min - l
                            result[0] = l
                            result[1] = min
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().smallestRange(
            listOf(
                listOf(4, 10, 15, 24, 26), listOf(0, 9, 12, 20), listOf(5, 18, 22, 30)
            )
        ).toList()

    }
}