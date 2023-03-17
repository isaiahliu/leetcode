package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun search(nums: IntArray, target: Int): Int {
            if (nums.size == 1) {
                return if (nums[0] == target) 0 else -1
            }

            if (nums[0] < nums[nums.lastIndex]) {
                return binarySearch(nums, 0, nums.lastIndex, target)
            } else {
                fun findMinIndex(leftIndex: Int, rightIndex: Int): Int {
                    if (leftIndex > rightIndex) {
                        return Int.MAX_VALUE
                    }

                    if (leftIndex == rightIndex) {
                        return if (nums[leftIndex] < nums[0]) leftIndex else Int.MAX_VALUE
                    }

                    val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                    return if (nums[midIndex] < nums[0]) {
                        findMinIndex(leftIndex, midIndex - 1).coerceAtMost(midIndex)
                    } else {
                        findMinIndex(midIndex + 1, rightIndex)
                    }
                }

                val minIndex = findMinIndex(0, nums.lastIndex)

                return if (target < nums[0]) {
                    binarySearch(nums, minIndex, nums.lastIndex, target)
                } else {
                    binarySearch(nums, 0, minIndex - 1, target)
                }
            }
        }

        fun binarySearch(nums: IntArray, leftIndex: Int, rightIndex: Int, target: Int): Int {
            if (leftIndex > rightIndex) {
                return -1
            }

            if (target > nums[rightIndex] || target < nums[leftIndex]) {
                return -1
            }

            if (leftIndex == rightIndex) {
                return if (nums[leftIndex] == target) leftIndex else -1
            }

            val midIndex = leftIndex + (rightIndex - leftIndex) / 2

            return if (nums[midIndex] == target) {
                midIndex
            } else if (nums[midIndex] > target) {
                binarySearch(nums, leftIndex, midIndex, target)
            } else {
                binarySearch(nums, midIndex + 1, rightIndex, target)
            }
        }
    }

    measureTimeMillis {
        println(Solution().search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 1))
    }.also { println("Time cost: ${it}ms") }
}

