package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun search(nums: IntArray, target: Int): Int {
            fun binarySearch(startIndex: Int, endIndex: Int): Int? {
                if (startIndex > endIndex) {
                    return null
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = nums[midIndex]

                return when {
                    midNum < target -> binarySearch(midIndex + 1, endIndex)
                    midNum > target -> binarySearch(startIndex, midIndex - 1)
                    else -> midIndex
                }
            }

            return binarySearch(0, nums.lastIndex) ?: -1
        }
    }

    measureTimeMillis {
        Solution().search(
            intArrayOf(), 1
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}