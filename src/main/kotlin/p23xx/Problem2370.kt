package p23xx

import util.expect

fun main() {
    class Solution {
        fun longestIdealString(s: String, k: Int): Int {
            val dp = IntArray(26)

            s.forEach {
                dp[it - 'a'] = (it - 'a' - k..it - 'a' + k).maxOf { dp.getOrNull(it) ?: 0 } + 1
            }

            return dp.max()
        }
    }

    expect {
        Solution().longestIdealString(
            "", 1
        )
    }
}