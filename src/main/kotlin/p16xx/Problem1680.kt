package p16xx

import util.expect

fun main() {
    class Solution {
        fun concatenatedBinary(n: Int): Int {
            var result = 0L
            val m = 1000000007

            for (num in 1..n) {
                result *= 2
                result *= Integer.highestOneBit(num)
                result %= m

                result += num
                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().concatenatedBinary(
            12
        )
    }
}

