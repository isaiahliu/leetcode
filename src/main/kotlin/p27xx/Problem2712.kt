package p27xx

import util.expect

fun main() {
    class Solution {
        fun minimumCost(s: String): Long {
            var left = 1
            var right = 1

            var result = 0L
            while (left + right <= s.length) {
                when {
                    s[left] == s[left - 1] -> {
                        left++
                    }

                    s[s.length - right] == s[s.lastIndex - right] -> {
                        right++
                    }

                    left < right -> {
                        result += left++
                    }

                    else -> {
                        result += right++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumCost(
            ""
        )
    }
}
