package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxValue(n: Int, index: Int, maxSum: Int): Int {
            fun binarySearch(start: Int, end: Int): Int {
                if (start > end) {
                    return -1
                }

                val mid = (start + end) / 2

                val leftSize = mid.coerceAtMost(index + 1).toLong()
                val rightSize = mid.coerceAtMost(n - index).toLong()

                val leftRemain = index + 1 - leftSize
                val rightRemain = n - index - rightSize

                val leftSum = (mid * 2 - leftSize + 1) * leftSize / 2
                val rightSum = (mid * 2 - rightSize + 1) * rightSize / 2

                val sum = leftSum + rightSum - mid + leftRemain + rightRemain

                return if (sum > maxSum) {
                    binarySearch(start, mid - 1)
                } else {
                    mid.coerceAtLeast(binarySearch(mid + 1, end))
                }
            }

            return binarySearch(1, maxSum)
        }
    }

    measureTimeMillis {
        Solution().maxValue(
            3, 0, 815094800
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
