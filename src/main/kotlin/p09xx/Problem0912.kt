package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sortArray(nums: IntArray): IntArray {
            fun mergeSort(startIndex: Int, endIndex: Int) {
                if (startIndex == endIndex) {
                    return
                }

                val midIndex = (startIndex + endIndex) / 2

                mergeSort(startIndex, midIndex)
                mergeSort(midIndex + 1, endIndex)

                val temp = IntArray(endIndex - startIndex + 1)

                var l = startIndex
                var r = midIndex + 1
                var index = 0
                while (l <= midIndex && r <= endIndex) {
                    temp[index++] = if (nums[l] < nums[r]) {
                        nums[l++]
                    } else {
                        nums[r++]
                    }
                }

                while (l <= midIndex) {
                    temp[index++] = nums[l++]
                }

                while (r <= endIndex) {
                    temp[index++] = nums[r++]
                }

                repeat(temp.size) {
                    nums[startIndex + it] = temp[it]
                }
            }

            return nums.also { mergeSort(0, nums.lastIndex) }
        }
    }

    measureTimeMillis {
        Solution().sortArray(
            intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}