package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun singleNonDuplicate(nums: IntArray): Int {
            if (nums.size == 1) {
                return nums[0]
            }

            fun binarySearch(startPair: Int, endPair: Int): Int {
                if (startPair == endPair) {
                    return startPair * 2
                }

                val midPair = (startPair + endPair) / 2
                val midIndex = midPair * 2

                return if (nums[midIndex] == nums[midIndex + 1]) {
                    binarySearch(midPair + 1, endPair)
                } else {
                    binarySearch(startPair, midPair)
                }
            }

            return nums[binarySearch(0, nums.size / 2)]
        }
    }

    measureTimeMillis {

        Solution().singleNonDuplicate(
            intArrayOf(2, 2, 3, 3, 1),
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}