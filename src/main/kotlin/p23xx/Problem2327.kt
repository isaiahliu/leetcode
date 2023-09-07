package p23xx

import util.expect

fun main() {
    class Solution {
        fun peopleAwareOfSecret(n: Int, delay: Int, forget: Int): Int {
            val cache = IntArray(n + 1) { -1 }

            val m = 1000000007
            fun dfs(remain: Int): Int {
                return when {
                    remain > n -> {
                        0
                    }

                    cache[remain] >= 0 -> {
                        cache[remain]
                    }

                    else -> {
                        var result = 0L

                        if (remain + forget > n) {
                            result++
                        }

                        for (d in remain + delay until remain + forget) {
                            result += dfs(d)
                            result %= m
                        }

                        cache[remain] = result.toInt()
                        result.toInt()
                    }
                }
            }

            return dfs(1)
        }
    }

    expect {
        Solution().peopleAwareOfSecret(
            4, 1, 3
        )
    }
}