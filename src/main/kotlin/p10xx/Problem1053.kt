package p10xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun prevPermOpt1(arr: IntArray): IntArray {
            fun binarySearch(startIndex: Int, endIndex: Int, target: Int): Int? {
                if (startIndex > endIndex) {
                    return null
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = arr[midIndex]

                return if (midNum >= target) {
                    binarySearch(startIndex, midIndex - 1, target)
                } else {
                    binarySearch(midIndex + 1, endIndex, target) ?: midIndex
                }
            }

            for (i in arr.lastIndex - 1 downTo 0) {
                if (arr[i] > arr[i + 1]) {
                    binarySearch(i + 1, arr.lastIndex, arr[i])?.also { nextNumIndex ->
                        val ri = (binarySearch(i + 1, nextNumIndex - 1, arr[nextNumIndex]) ?: i) + 1

                        val t = arr[ri]
                        arr[ri] = arr[i]
                        arr[i] = t

                        return arr
                    }
                }
            }

            return arr
        }
    }

    measureTimeMillis {
        Solution().prevPermOpt1(
            intArrayOf(3, 1, 1, 3)
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}