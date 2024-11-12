package p32xx

import util.expect

fun main() {
    class Solution {
        fun countKConstraintSubstrings(s: String, k: Int): Int {
            var result = 0

            for (l in s.indices) {
                val counts = intArrayOf(0, 0)

                for (r in l until s.length) {
                    counts[s[r] - '0']++

                    if (counts.min() > k) {
                        break
                    }

                    result++
                }
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
