package p24xx

import util.expect

fun main() {
    class Solution {
        fun countGoodStrings(low: Int, high: Int, zero: Int, one: Int): Int {
            val m = 1000000007

            var result = 0L

            val dp = arrayListOf(1L)

            for (i in 1..high) {
                val next = (dp.getOrElse(i - zero) { 0L } + dp.getOrElse(i - one) { 0L }) % m
                dp.add(next)

                if (i in low..high) {
                    result += next
                    result %= m
                }
            }

            return result.toInt()
        }
    }

    expect {
        Solution().countGoodStrings(
            1, 100000, 1, 1
        )
    }
}