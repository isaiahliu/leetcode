package p18xx

import java.math.BigInteger
import util.expect

fun main() {
    class Solution {
        fun makeStringSorted(s: String): Int {
            val m = 1000000007
            val mi = 1000000007.toBigInteger()

            var result = 0L
            var arrangement = BigInteger.ONE
            var dup = BigInteger.ONE
            val counts = hashMapOf<Char, Int>()
            for (index in s.lastIndex downTo 0) {
                (s.lastIndex - index).takeIf { it > 0 }?.toBigInteger()?.also {
                    arrangement *= it
                    arrangement %= mi
                }

                val char = s[index]

                counts[char] = ((counts[char] ?: 0) + 1).also {
                    dup *= it.toBigInteger()
                    dup %= mi
                }

                counts.forEach { (c, count) ->
                    when {
                        c < char -> {
                            result += (arrangement * count.toBigInteger() * dup.modInverse(mi) % mi).toLong()
                            result %= m
                        }
                    }
                }

            }

            return result.toInt()
        }
    }

    expect {
        Solution().makeStringSorted(
            "leetcodeleetcodeleetcode"
        )

    }
}
