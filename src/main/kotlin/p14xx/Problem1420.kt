package p14xx

import util.expect

fun main() {
    class Solution {
        fun numOfArrays(n: Int, m: Int, k: Int): Int {
            val mod = 1000000007

            val cache = hashMapOf<Pair<Pair<Int, Int>, Int>, Int>()
            fun dfs(max: Int, length: Int, inc: Int): Int {
                val cacheKey = max to length to inc
                return when {
                    inc > length -> {
                        0
                    }

                    length == 0 -> {
                        1
                    }

                    cacheKey in cache -> {
                        cache[cacheKey] ?: 0
                    }

                    else -> {
                        var result = 0L

                        repeat(max) {
                            result += dfs(max, length - 1, inc)
                            result %= mod
                        }

                        if (inc > 0) {
                            (max + 1..m).forEach {
                                result += dfs(it, length - 1, inc - 1)
                                result %= mod
                            }
                        }

                        cache[cacheKey] = result.toInt()

                        result.toInt()
                    }
                }
            }

            return dfs(0, n, k)
        }
    }

    expect {
        Solution().numOfArrays(
            37, 17, 7
        )
    }
}

