package p21xx

import util.expect

fun main() {
    class Solution {
        fun minimumTime(s: String): Int {
            val oneCount = s.count { it == '1' }

            if (oneCount == 0) {
                return 0
            }

            val leftDp = IntArray(oneCount)
            var leftIndex = 0
            val rightDp = IntArray(oneCount)
            var rightIndex = oneCount - 1

            for (i in s.indices) {
                if (s[i] == '1') {
                    leftDp[leftIndex] = (i + 1).coerceAtMost((leftDp.getOrNull(leftIndex - 1) ?: 0) + 2)
                    leftIndex++
                }

                if (s[s.lastIndex - i] == '1') {
                    rightDp[rightIndex] = (i + 1).coerceAtMost((rightDp.getOrNull(rightIndex + 1) ?: 0) + 2)
                    rightIndex--
                }
            }

            var result = leftDp[oneCount - 1].coerceAtMost(rightDp[0])

            for (i in 0 until oneCount - 1) {
                result = result.coerceAtMost(leftDp[i] + rightDp[i + 1])
            }

            return result
        }
    }

    expect {
        Solution().minimumTime(
            "1100101"
        )
    }
}