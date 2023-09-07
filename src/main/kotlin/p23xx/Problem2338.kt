package p23xx

import util.expect

fun main() {
    class Solution {
        fun idealArrays(n: Int, maxValue: Int): Int {
            val m = 1000000007
            val mi = m.toBigInteger()

            fun Int.arrangement(start: Int = 1): Int {
                var result = 1L

                for (num in start..this) {
                    result *= num
                    result %= m
                }

                return result.toInt()
            }

            fun combine(cm: Int, cn: Int): Int {
                val cmn = cm - cn

                return ((cm.arrangement(cn.coerceAtLeast(cmn) + 1) * cn.coerceAtMost(cmn).arrangement().toBigInteger()
                    .modInverse(mi).toLong()) % m).toInt()
            }

            val cache = hashMapOf<Pair<Int, Int>, Int>()
            fun dfs(lastNum: Int, pickedNums: Int): Int {
                if (pickedNums == n) {
                    return 1
                }

                val cacheKey = lastNum to pickedNums
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = combine(n - 1, pickedNums - 1).toLong()

                for (next in lastNum + lastNum..maxValue step lastNum) {
                    result += dfs(next, pickedNums + 1)
                    result %= m
                }

                cache[cacheKey] = result.toInt()
                return result.toInt()
            }

            var result = 0L
            for (start in 1..maxValue) {
                result += dfs(start, 1)
                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().idealArrays(
            5, 9
        )
    }
}