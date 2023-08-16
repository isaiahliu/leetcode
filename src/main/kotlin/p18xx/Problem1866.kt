package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun rearrangeSticks(n: Int, k: Int): Int {
            val m = 1000000007
            val mi = 1000000007.toBigInteger()

            val factorialCache = IntArray(n + 1)
            factorialCache[0] = 1
            fun Int.factorial(): Int {
                if (factorialCache[this] > 0) {
                    return factorialCache[this]
                }

                return ((this.toLong() * (this - 1).factorial()) % m).toInt().also {
                    factorialCache[this] = it
                }
            }

            val cache = Array(n + 1) { IntArray(k + 1) { -1 } }
            fun dfs(num: Int, remain: Int): Int {
                if (num == 0) {
                    return 1
                }

                if (cache[num][remain] >= 0) {
                    return cache[num][remain]
                }

                val result = when {
                    remain == 0 -> {
                        (((n - 1).factorial().toBigInteger() * (n - num - 1).factorial().toBigInteger()
                            .modInverse(mi)) % mi).toLong()
                    }

                    num > remain -> {
                        (n - num).toLong() * dfs(num - 1, remain) + dfs(num - 1, remain - 1)
                    }

                    else -> {
                        1L
                    }
                } % m

                cache[num][remain] = result.toInt()
                return result.toInt()
            }

            return dfs(n - 1, k - 1)
        }
    }

    measureTimeMillis {
        Solution().rearrangeSticks(
            4, 2
        ).also { println("${it} should be $it") }
        Solution().rearrangeSticks(
            3, 2
        ).also { println("${it} should be 3") }

        Solution().rearrangeSticks(
            5, 5
        ).also { println("${it} should be 1") }
        Solution().rearrangeSticks(
            20, 11
        ).also { println("${it} should be 647427950") }
    }.also { println("Time cost: ${it}ms") }
}
