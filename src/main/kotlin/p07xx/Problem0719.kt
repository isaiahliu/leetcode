package p07xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun smallestDistancePair(nums: IntArray, k: Int): Int {
            nums.sort()

            fun binarySearch(startIndex: Int, endIndex: Int, target: Int): Int? {
                if (startIndex > endIndex) {
                    return null
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = nums[midIndex]

                return if (midNum > target) {
                    binarySearch(startIndex, midIndex - 1, target)
                } else {
                    binarySearch(midIndex + 1, endIndex, target) ?: midIndex
                }
            }

            fun find(start: Int, end: Int): Int? {
                if (start > end) {
                    return null
                }

                val mid = (start + end) / 2

                var count = 0
                for ((index, num) in nums.withIndex()) {
                    binarySearch(index + 1, nums.lastIndex, mid + num)?.let { it - index }?.also {
                        count += it
                    }
                }

                return if (count < k) {
                    find(mid + 1, end)
                } else {
                    find(start, mid - 1) ?: mid
                }
            }

            return find(0, nums[nums.lastIndex] - nums[0]) ?: 0
        }
    }

    measureTimeMillis {
        Solution().smallestDistancePair(
            intArrayOf(
                1, 4, 5, 6, 7, 8, 9, 9, 10, 10,
            ), 18
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}