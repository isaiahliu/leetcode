package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun reversePairs(nums: IntArray): Int {
            var result = 0

            fun binarySearch(startIndex: Int, endIndex: Int, target: Int): Int {
                if (startIndex > endIndex) {
                    return nums.size
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = nums[midIndex]

                return if (midNum <= target) {
                    binarySearch(midIndex + 1, endIndex, target)
                } else {
                    binarySearch(startIndex, midIndex - 1, target).coerceAtMost(midIndex)
                }
            }

            fun mergeSort(startIndex: Int, endIndex: Int) {
                if (startIndex >= endIndex) {
                    return
                }

                val midIndex = (startIndex + endIndex) / 2

                mergeSort(startIndex, midIndex)
                mergeSort(midIndex + 1, endIndex)

                var leftIndex = startIndex
                var rightIndex = midIndex + 1

                val temp = IntArray(endIndex - startIndex + 1)
                var tempIndex = 0
                var searchStartIndex = startIndex
                while (leftIndex <= midIndex || rightIndex <= endIndex) {
                    temp[tempIndex++] =
                        if (leftIndex <= midIndex && (rightIndex > endIndex || nums[leftIndex] < nums[rightIndex])) {
                            nums[leftIndex++]
                        } else {
                            nums[rightIndex++].also {
                                if (it < Int.MIN_VALUE / 2) {
                                    result += midIndex - searchStartIndex + 1
                                } else if (it <= Int.MAX_VALUE / 2) {
                                    searchStartIndex = binarySearch(searchStartIndex, midIndex, it * 2)
                                    (midIndex - searchStartIndex + 1).takeIf { it > 0 }?.also {
                                        result += it
                                    }
                                }
                            }
                        }
                }

                temp.forEachIndexed { index, i ->
                    nums[startIndex + index] = i
                }
            }

            mergeSort(0, nums.lastIndex)

            return result
        }
    }

    measureTimeMillis {
        Solution().reversePairs(
            intArrayOf(-2147483648, -1073741824)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}