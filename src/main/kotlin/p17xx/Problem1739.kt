package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumBoxes(n: Int): Int {
            val sums = arrayListOf(0)

            var sum = 0
            var sumsum = 0
            var add = 0
            while (sumsum + sum + add <= n) {
                add++
                sum += add
                sums.add(sum)

                sumsum += sum
            }

            val targetNum = n - sumsum

            fun binarySearch(
                startIndex: Int = 0,
                endIndex: Int = sums.lastIndex
            ): Int {
                if (startIndex > endIndex) {
                    return sums.size
                }

                val midIndex = (startIndex + endIndex) / 2
                val midNum = sums[midIndex]

                return if (targetNum <= midNum) {
                    midIndex.coerceAtMost(binarySearch(startIndex, midIndex - 1))
                } else {
                    binarySearch(midIndex + 1, endIndex)
                }
            }

            return sums[add] + binarySearch()
        }
    }

    measureTimeMillis {
        Solution().minimumBoxes(
            15
        ).also { println("${it} should be 9") }
        Solution().minimumBoxes(
            3
        ).also { println("${it} should be 3") }

        Solution().minimumBoxes(
            10
        ).also { println("${it} should be 6") }

    }.also { println("Time cost: ${it}ms") }
}
