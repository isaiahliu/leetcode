package p22xx

import util.expect

fun main() {
    class Solution {
        fun countTexts(pressedKeys: String): Int {
            val m = 1000000007

            val charCounts = arrayOf(0, 0, 3, 3, 3, 3, 3, 4, 3, 4)

            val cache = Array(pressedKeys.length + 1) {
                longArrayOf(-1, -1)
            }

            fun dfs(remain: Int, charCount: Int): Long {
                return when {
                    remain == 0 -> {
                        1L
                    }

                    cache[remain][charCount - 3] >= 0 -> {
                        cache[remain][charCount - 3]
                    }

                    else -> {
                        var result = 0L

                        (1..charCount.coerceAtMost(remain)).forEach {
                            result += dfs(remain - it, charCount)
                            result %= m
                        }

                        cache[remain][charCount - 3] = result

                        result
                    }
                }
            }

            var result = 1L

            var pre = pressedKeys[0]
            var count = 0

            "$pressedKeys ".forEach {
                if (it == pre) {
                    count++
                } else {
                    result *= dfs(count, charCounts[pre - '0'])
                    result %= m

                    count = 1
                    pre = it
                }
            }

            return result.toInt()
        }
    }

    expect {
        Solution().countTexts(
            "22233"
        )
    }
}