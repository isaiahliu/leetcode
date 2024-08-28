package p31xx

import util.expect
import kotlin.math.max
import kotlin.math.min

fun main() {
    class Solution {
        fun minimumSubstringsInPartition(s: String): Int {
            val dp = IntArray(s.length) { it + 1 }

            for (i in s.indices) {
                val counts = hashMapOf<Char, Int>()
                var maxCount = 0
                for (j in i downTo 0) {
                    counts[s[j]] = ((counts[s[j]] ?: 0) + 1).also {
                        maxCount = max(maxCount, it)
                    }

                    if (maxCount * counts.size == (i - j + 1)) {
                        dp[i] = min(dp[i], (dp.getOrElse(j - 1) { 0 } + 1))
                    }
                }
            }
            return dp[s.lastIndex]
        }
    }

    expect {
        Solution().minimumSubstringsInPartition(
            "a"
        )
    }
}
