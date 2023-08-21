package p03xx

import util.expect

fun main() {
    class Solution {
        fun isSubsequence(s: String, t: String): Boolean {
            val maxSkipCount = t.length - s.length

            if (maxSkipCount < 0) {
                return false
            }

            var leftIndex = 0
            var rightIndex = 0
            var skipped = 0
            while (leftIndex < s.length) {
                if (s[leftIndex] == t[rightIndex]) {
                    leftIndex++
                    rightIndex++
                } else if (skipped < maxSkipCount) {
                    skipped++
                    rightIndex++
                } else {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().isSubsequence(
            "", ""
        )
    }
}

