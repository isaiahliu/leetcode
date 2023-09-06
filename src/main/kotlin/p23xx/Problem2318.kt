package p23xx

import util.expect

fun main() {
    class Solution {
        fun distinctSequences(n: Int): Int {
            val nexts = arrayOf(
                setOf(1, 2, 3, 4, 5, 6),
                setOf(2, 3, 4, 5, 6),
                setOf(1, 3, 5),
                setOf(1, 2, 4, 5),
                setOf(1, 3, 5),
                setOf(1, 2, 3, 4, 6),
                setOf(1, 5)
            )

            val m = 1000000007
            val cache = Array(n + 1) {
                Array(7) {
                    IntArray(7) { -1 }
                }
            }

            fun dfs(remain: Int, prev1: Int, prev2: Int): Int {
                return if (remain == 0) {
                    1
                } else if (cache[remain][prev1][prev2] >= 0) {
                    cache[remain][prev1][prev2]
                } else {
                    var result = 0L

                    nexts[prev2].forEach {
                        if (it != prev1) {
                            result += dfs(remain - 1, prev2, it)
                            result %= m
                        }
                    }

                    cache[remain][prev1][prev2] = result.toInt()
                    result.toInt()
                }
            }

            return dfs(n, 0, 0)
        }
    }

    expect {
        Solution().distinctSequences(
            4
        )
    }
}