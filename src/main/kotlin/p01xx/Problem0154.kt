package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findMin(nums: IntArray): Int {
            var min = nums[0]

            fun findMinIndex(leftIndex: Int, rightIndex: Int) {
                if (leftIndex > rightIndex) {
                    return
                }

                if (leftIndex == rightIndex) {
                    min = min.coerceAtMost(nums[leftIndex])
                    return
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                min = min.coerceAtMost(nums[midIndex])
                if (nums[midIndex] <= nums[0]) {
                    findMinIndex(leftIndex, midIndex - 1)
                }

                if (nums[midIndex] >= nums[0]) {
                    findMinIndex(midIndex + 1, rightIndex)
                }
            }

            findMinIndex(0, nums.lastIndex)

            return min
        }
    }

    measureTimeMillis {
        Solution().findMin(
            intArrayOf(3, 4, 5, 1, 2)
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

