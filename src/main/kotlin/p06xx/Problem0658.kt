package p06xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
            if (k == arr.size) {
                return arr.toList()
            }

            fun binarySearch(startIndex: Int, endIndex: Int): Int? {
                if (startIndex > endIndex) {
                    return null
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = arr[midIndex]

                return if (midNum < x) {
                    binarySearch(midIndex + 1, endIndex)
                } else {
                    binarySearch(startIndex, midIndex - 1) ?: midIndex
                }
            }

            var rightIndex = binarySearch(0, arr.lastIndex) ?: arr.lastIndex
            var leftIndex = rightIndex - 1

            val result = LinkedList<Int>()
            if (arr[rightIndex] == k) {
                result.add(k)
                rightIndex++
            }

            while (result.size < k) {
                val left = arr.getOrNull(leftIndex)
                val right = arr.getOrNull(rightIndex)

                if (left == null || right != null && right - x < x - left) {
                    result.add(right ?: 0)
                    rightIndex++
                } else {
                    result.push(left)
                    leftIndex--
                }
            }

            return result
        }
    }

    expect {
        Solution().findClosestElements(
            intArrayOf(1, 2, 3, 4, 5), 4, -1
        )
    }
}