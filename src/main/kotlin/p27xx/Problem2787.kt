package p27xx

import util.expect

fun main() {
    class Solution {
        fun numberOfWays(n: Int, x: Int): Int {
            val factors = arrayListOf(0)
            var t = 1
            while (true) {
                var num = 1
                repeat(x) {
                    num *= t
                }

                if (num > n) {
                    break
                }

                factors += num
                t++
            }

            val m = 1000000007

            val cache = Array(n + 1) { LongArray(factors.size + 1) { -1 } }
            fun dfs(num: Int, startNum: Int): Long {
                return when {
                    num == 0 -> {
                        1
                    }

                    cache[num][startNum] >= 0 -> {
                        cache[num][startNum]
                    }

                    else -> {
                        var result = 0L

                        for (i in startNum + 1 until factors.size) {
                            if (num < factors[i]) {
                                break
                            }

                            result += dfs(num - factors[i], i)
                            result %= m
                        }

                        cache[num][startNum] = result
                        result
                    }
                }
            }

            return dfs(n, 0).toInt()
        }
    }

    expect {
        Solution().numberOfWays(
            10, 2
        )
    }
}
