package p21xx

import util.expect

fun main() {
    class Solution {
        fun mostPoints(questions: Array<IntArray>): Long {
            val dp = LongArray(questions.size)

            for (i in questions.lastIndex downTo 0) {
                dp[i] = questions[i][0] + (dp.getOrNull(i + questions[i][1] + 1) ?: 0)

                dp.getOrNull(i + 1)?.also {
                    dp[i] = dp[i].coerceAtLeast(it)
                }
            }

            return dp[0]
        }
    }

    expect {
        Solution().mostPoints(
            arrayOf()
        )
    }
}