package p03xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun lengthOfLIS(nums: IntArray): Int {
            val subNums = IntArray(nums.size)

            subNums[0] = nums[0]
            var rightIndex = 0

            fun binarySearch(startIndex: Int, endIndex: Int, num: Int): Int {
                if (startIndex > endIndex) {
                    return Int.MAX_VALUE
                }

                val midIndex = startIndex + (endIndex - startIndex) / 2
                val midNum = subNums[midIndex]

                return when {
                    midNum > num -> {
                        binarySearch(startIndex, midIndex - 1, num).coerceAtMost(midIndex)
                    }

                    midNum < num -> {
                        binarySearch(midIndex + 1, endIndex, num)
                    }

                    else -> {
                        midIndex
                    }
                }
            }

            nums.forEach {
                when {
                    it < subNums[rightIndex] -> {
                        subNums[binarySearch(0, rightIndex, it)] = it
                    }

                    it > subNums[rightIndex] -> {
                        rightIndex++
                        subNums[rightIndex] = it
                    }

                    else -> {}
                }
            }

            return rightIndex + 1
        }
    }

    measureTimeMillis {
        Solution().lengthOfLIS(intArrayOf(18, 55, 66, 2, 3, 54)).also { println(it) }
    }
}

