package p27xx

import util.expect

fun main() {
    class Solution {
        fun minimumCost(s: String): Long {
            var left = 0
            var right = s.lastIndex

            var result = 0L
            while (left < right) {
                when {
                    s[left + 1] == s[left] -> {
                        left++
                    }

                    s[right - 1] == s[right] -> {
                        right--
                    }

                    left < s.lastIndex - right -> {
                        left++
                        result += left
                    }

                    else -> {
                        right--
                        result += s.lastIndex - right
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
