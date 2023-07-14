package p12xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countVowelPermutation(n: Int): Int {
            val dp = LongArray(5) { 1L }
            val m = 1000000007

            repeat(n - 1) {
                val (a, e, i, o, u) = dp

                dp[0] = (e + i + u) % m //a
                dp[1] = (a + i) % m //e
                dp[2] = (e + o) % m //i
                dp[3] = (i) % m //o
                dp[4] = (i + o) % m //u
            }

            return (dp.sum() % m).toInt()
        }
    }

    measureTimeMillis {
        Solution().countVowelPermutation(
            5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
