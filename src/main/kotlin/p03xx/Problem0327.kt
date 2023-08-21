package p03xx

import util.expect

fun main() {
    class Solution {
        fun countRangeSum(nums: IntArray, lower: Int, upper: Int): Int {
            var sum = 0L
            val sums = LongArray(nums.size + 1) { i ->
                sum.also { nums.getOrNull(i)?.also { sum += it } }
            }

            fun binarySearch(leftIndex: Int, rightIndex: Int, target: Long, leftBound: Boolean): Int {
                if (leftIndex > rightIndex) {
                    return if (leftBound) rightIndex + 1 else leftIndex - 1
                }

                val midIndex = (leftIndex + rightIndex) / 2
                val midNum = sums[midIndex]
                return if (leftBound) {
                    if (midNum < target) {
                        binarySearch(midIndex + 1, rightIndex, target, leftBound)
                    } else {
                        midIndex.coerceAtMost(binarySearch(leftIndex, midIndex - 1, target, leftBound))
                    }
                } else {
                    if (midNum > target) {
                        binarySearch(leftIndex, midIndex - 1, target, leftBound)
                    } else {
                        midIndex.coerceAtLeast(binarySearch(midIndex + 1, rightIndex, target, leftBound))
                    }
                }
            }

            var result = 0
            fun reduceSort(leftIndex: Int, rightIndex: Int) {
                if (leftIndex >= rightIndex) {
                    return
                }

                val midIndex = (leftIndex + rightIndex) / 2

                reduceSort(leftIndex, midIndex)
                reduceSort(midIndex + 1, rightIndex)

                for (l in leftIndex..midIndex) {
                    val leftSum = sums[l]

                    val leftTarget = binarySearch(midIndex + 1, rightIndex, leftSum + lower, true)
                    val rightTarget = binarySearch(midIndex + 1, rightIndex, leftSum + upper, false)

                    result += (rightTarget - leftTarget + 1).coerceAtLeast(0)
                }

                val tempArr = LongArray(rightIndex - leftIndex + 1)
                var tempIndex = 0

                var l = leftIndex
                var r = midIndex + 1

                while (l <= midIndex && r <= rightIndex) {
                    tempArr[tempIndex++] = if (sums[l] < sums[r]) {
                        sums[l++]
                    } else {
                        sums[r++]
                    }
                }
                while (l <= midIndex) {
                    tempArr[tempIndex++] = sums[l++]
                }

                while (r <= rightIndex) {
                    tempArr[tempIndex++] = sums[r++]
                }

                tempArr.forEachIndexed { index, i ->
                    sums[leftIndex + index] = i
                }
            }

            reduceSort(0, sums.lastIndex)

            return result
        }
    }

    expect {
        Solution().countRangeSum(intArrayOf(-2, 5, -1), -2, 2)
    }
}

