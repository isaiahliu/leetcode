package p22xx

import util.expect
import util.input

fun main() {
    class Solution {
        fun maximumBeauty(flowers: IntArray, newFlowers: Long, target: Int, full: Int, partial: Int): Long {
            var fullScore = 0L
            val partials = arrayListOf<Int>()

            var gap = 0L
            flowers.forEach {
                if (it < target) {
                    partials.add(it)
                    gap += target - it
                } else {
                    fullScore += full
                }
            }

            if (gap <= newFlowers) {
                return if (partials.isEmpty()) {
                    flowers.size.toLong() * full
                } else {
                    (flowers.size - 1).toLong() * full + full.toLong().coerceAtLeast(partial.toLong() * (target - 1))
                }
            }

            partials.sort()

            var result = fullScore

            val leftSums = LongArray(partials.size)
            var leftSum = 0L

            for (index in partials.indices) {
                val leftNum = partials[index]

                leftSum += leftNum

                leftSums[index] = leftNum.toLong() * (index + 1) - leftSum
            }

            var rightCost = 0L
            var rightScore = 0L
            for (index in partials.lastIndex downTo 0) {
                val remain = newFlowers - rightCost

                if (remain < 0) {
                    break
                }

                val leftRequire = leftSums[index]

                if (remain >= leftRequire) {
                    result =
                        result.coerceAtLeast((partials[index] + (remain - leftRequire) / (index + 1)) * partial + fullScore + rightScore)
                } else {
                    var l = 0
                    var r = index
                    var leftIndex = 0
                    while (l <= r) {
                        val m = l + (r - l) / 2

                        if (leftSums[m] <= remain) {
                            leftIndex = m
                            l = m + 1
                        } else {
                            r = m - 1
                        }
                    }

                    result =
                        result.coerceAtLeast((partials[leftIndex] + (remain - leftSums[leftIndex]) / (leftIndex + 1)) * partial.toLong() + fullScore + rightScore)
                }

                rightCost += target - partials[index]
                rightScore += full
            }

            return result
        }
    }

    expect(10000000000) {
        Solution().maximumBeauty(
            input.first().split(",").map { it.toInt() }.toIntArray(), 10000000000, 100000, 100000, 100000
        )
    }

    expect(47) {
        Solution().maximumBeauty(
            intArrayOf(5, 19, 1, 1, 6, 10, 18, 12, 20, 10, 11), 6L, 20, 3, 11
        )
    }

    expect(14) {
        Solution().maximumBeauty(
            intArrayOf(1, 3, 1, 1), 7L, 6, 12, 1
        )
    }

    expect(14) {
        Solution().maximumBeauty(
            intArrayOf(20, 1, 15, 17, 10, 2, 4, 16, 15, 11), 2, 20, 10, 2
        )
    }

    expect(54) {
        Solution().maximumBeauty(
            intArrayOf(8, 2), 24, 18, 6, 3
        )
    }

}