package p19xx

import util.expect

fun main() {
    class Solution {
        fun countGoodNumbers(n: Long): Int {
            val m = 1000000007.toBigInteger()

            return (5.toBigInteger().modPow((n / 2 + n % 2).toBigInteger(), m) * 4.toBigInteger()
                .modPow((n / 2).toBigInteger(), m) % m).toInt()
        }
    }

    expect {
        Solution().countGoodNumbers(
            1
        )
    }
}