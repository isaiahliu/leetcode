package p11xx

import util.expect

fun main() {
    class Solution {
        fun longestWPI(hours: IntArray): Int {
            val sumMap = hashMapOf<Int, Int>()

            var sum = 0
            var endIndex = 0
            var hasPositive = false
            hours.forEachIndexed { index, i ->
                if (i > 8) {
                    hasPositive = true
                    sum++
                } else {
                    sum--
                }

                if (sum > 0) {
                    endIndex = index
                }
                sumMap[sum] = index
            }

            if (!hasPositive) {
                return 0
            }
            var leftIndex = 0
            var result = endIndex
            var removedSum = 0
            hours.forEach { i ->
                leftIndex++
                if (i > 8) {
                    removedSum++
                } else {
                    removedSum--

                    sumMap[1 + removedSum]?.takeIf { it > endIndex }?.also {
                        endIndex = it

                        result = result.coerceAtLeast(endIndex - leftIndex)
                    }
                }
            }

            return result + 1
        }
    }

    expect {
        Solution().longestWPI(intArrayOf(6, 6, 9))
    }
}

