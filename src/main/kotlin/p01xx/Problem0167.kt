package p01xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun twoSum(numbers: IntArray, target: Int): IntArray {
            fun binarySearch(leftIndex: Int, rightIndex: Int, t: Int): Int {
                if (leftIndex > rightIndex) {
                    return -1
                }

                if (t > numbers[rightIndex] || t < numbers[leftIndex]) {
                    return -1
                }

                if (leftIndex == rightIndex) {
                    return if (numbers[leftIndex] == t) leftIndex else -1
                }

                val midIndex = leftIndex + (rightIndex - leftIndex) / 2

                return if (numbers[midIndex] == t) {
                    midIndex
                } else if (numbers[midIndex] > t) {
                    binarySearch(leftIndex, midIndex, t)
                } else {
                    binarySearch(midIndex + 1, rightIndex, t)
                }
            }

            var lastNum = numbers[0] - 1
            for (leftIndex in numbers.indices) {
                if (numbers[leftIndex] == lastNum) {
                    continue
                }

                val rightIndex = binarySearch(leftIndex + 1, numbers.size - 1, target - numbers[leftIndex])

                if (rightIndex > -1) {
                    return intArrayOf(leftIndex + 1, rightIndex + 1)
                }

                lastNum = numbers[leftIndex]
            }

            return intArrayOf(-1, -1)
        }
    }

    measureTimeMillis {
        Solution().twoSum(
            intArrayOf(2, 7), 9
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

