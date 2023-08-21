package p06xx

import util.expect

fun main() {
    class Solution {
        fun countBinarySubstrings(s: String): Int {
            var lastCount = 0
            var result = 0
            var count = 1
            for (i in 1 until s.length) {
                if (s[i] == s[i - 1]) {
                    count++

                    if (count <= lastCount) {
                        result++
                    }
                } else {
                    lastCount = count
                    count = 1
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().countBinarySubstrings(
            "00110011"
        )
    }
}