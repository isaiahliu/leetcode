package p19xx

import util.expect

fun main() {
    class Solution {
        fun numberOfUniqueGoodSubsequences(binary: String): Int {
            val m = 1000000007L
            val p = longArrayOf(
                0L,
                0L
            )

            var hasZero = false
            binary.forEach {
                p[it - '0'] = p[0] + p[1]
                if (it == '0') {
                    hasZero = true
                } else {
                    p[1]++
                }

                p[it - '0'] %= m
            }

            if (hasZero) {
                p[0]++
            }

            return ((p[0] + p[1]) % m).toInt()
        }
    }

    expect {
        Solution().numberOfUniqueGoodSubsequences(
            "10"
        )
    }
}