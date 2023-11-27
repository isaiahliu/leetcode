package p25xx

import util.expect

fun main() {
    class Solution {
        fun countAnagrams(s: String): Int {
            val m = 1000000007
            val mi = m.toBigInteger()

            fun Int.factorial(): Long {
                return (1..this).fold(1L) { a, b -> a * b % m }
            }

            return s.split(" ").fold(1L) { a, b ->
                var result = b.length.factorial()

                b.groupingBy { it }.eachCount().values.forEach {
                    result *= it.factorial().toBigInteger().modInverse(mi).toLong()
                    result %= m
                }

                a * result % m
            }.toInt()
        }
    }

    expect {
        Solution().countAnagrams(
            "too hot"
        )
    }
}