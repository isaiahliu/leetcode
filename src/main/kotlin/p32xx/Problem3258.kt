package p32xx

import util.expect

fun main() {
    class Solution {
        fun countKConstraintSubstrings(s: String, k: Int): Int {
            val counts = intArrayOf(0, 0)

            var from = 0
            var to = -1

            var result = 0
            while (to < s.lastIndex) {
                to++
                counts[s[to] - '0']++

                while (counts.min() > k) {
                    counts[s[from++] - '0']--
                }

                result += to - from + 1
            }

            return result
        }
    }

    expect {
        Solution().countKConstraintSubstrings(
            "", 1
        )
    }
}
