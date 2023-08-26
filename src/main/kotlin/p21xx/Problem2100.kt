package p21xx

import util.expect

fun main() {
    class Solution {
        fun goodDaysToRobBank(security: IntArray, time: Int): List<Int> {
            if (time == 0) {
                return security.indices.toList()
            }

            val rightSums = IntArray(security.size)

            for (i in security.lastIndex - 1 downTo 0) {
                if (security[i] <= security[i + 1]) {
                    rightSums[i] = rightSums[i + 1] + 1
                }
            }

            var leftSum = 0

            val result = arrayListOf<Int>()
            for (i in 1 until security.size) {
                if (security[i] <= security[i - 1]) {
                    leftSum++

                    if (leftSum >= time && rightSums[i] >= time) {
                        result.add(i)
                    }
                } else {
                    leftSum = 0
                }
            }

            return result
        }
    }

    expect {
        Solution().goodDaysToRobBank(
            intArrayOf(5, 3, 3, 3, 5, 6, 2), 2
        )
    }
}