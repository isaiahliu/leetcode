package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun peakIndexInMountainArray(arr: IntArray): Int {
            fun binarySearch(startIndex: Int, endIndex: Int): Int {
                val midIndex = (startIndex + endIndex) / 2
                val midNum = arr[midIndex]

                val gtLeft = midNum > arr[midIndex - 1]
                val gtRight = midNum > arr[midIndex + 1]

                return when {
                    gtLeft && gtRight -> midIndex
                    gtLeft -> binarySearch(midIndex + 1, endIndex)
                    else -> binarySearch(startIndex, midIndex - 1)
                }
            }

            return binarySearch(1, arr.lastIndex - 1)
        }
    }

    measureTimeMillis {
        Solution().peakIndexInMountainArray(
            intArrayOf(0, 1, 0)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}