package p27xx

import util.expect

fun main() {
    class Solution {
        fun count(num1: String, num2: String, min_sum: Int, max_sum: Int): Int {
            val size = maxOf(num1.length, num2.length)

            val n1 = num1.padStart(size, '0')
            val n2 = num2.padStart(size, '0')

            val FREE = 0
            val LOW = 1
            val HIGH = 2
            val BOTH = 3

            val m = 1000000007

            val cache = Array(size + 1) {
                Array(max_sum + 1) {
                    LongArray(4) { -1 }
                }
            }

            fun dfs(pos: Int, sum: Int, status: Int): Long {
                return when {
                    sum > max_sum -> 0L
                    pos == size && sum >= min_sum -> 1
                    pos == size -> 0
                    cache[pos][sum][status] != -1L -> cache[pos][sum][status]
                    else -> {
                        var min = 0
                        var max = 9

                        var result = 0L

                        if (status and LOW > 0) {
                            min = n1[pos] - '0'
                        }

                        if (status and HIGH > 0) {
                            max = n2[pos] - '0'
                        }

                        if (min == max) {
                            result += dfs(pos + 1, sum + min, status)
                        } else {
                            result += dfs(pos + 1, sum + min, status and LOW)
                            result += dfs(pos + 1, sum + max, status and HIGH)
                            (min + 1 until max).forEach {
                                result += dfs(pos + 1, sum + it, FREE)
                            }
                        }
                        result %= m

                        cache[pos][sum][status] = result

                        result
                    }
                }
            }

            return dfs(0, 0, BOTH).toInt()
        }
    }

    expect(883045899) {
        Solution().count(
            "4179205230", "7748704426", 8, 46
        )
    }
}
