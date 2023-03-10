package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countSmaller(nums: IntArray): List<Int> {
            val result = IntArray(nums.size)

            val numList = arrayListOf(nums[nums.size - 1])

            fun binarySearch(leftIndex: Int, rightIndex: Int, target: Int): Int {
                if (leftIndex > rightIndex) {
                    return numList.size
                }

                val midIndex = (leftIndex + rightIndex) / 2

                return if (numList[midIndex] < target) {
                    binarySearch(midIndex + 1, rightIndex, target)
                } else {
                    midIndex.coerceAtMost(binarySearch(leftIndex, midIndex - 1, target))
                }
            }

            for (i in nums.size - 2 downTo 0) {
                val num = nums[i]
                val index = binarySearch(0, numList.size - 1, num)

                numList.add(index, num)
                result[i] = index
            }

            return result.toList()
        }
    }

    measureTimeMillis {
        Solution().countSmaller(
            intArrayOf(-1, -1)
        ).also { println(it) }
    }
}

