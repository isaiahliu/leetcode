package p05xx

import java.math.BigInteger
import util.expect

fun main() {
    class Solution {
        fun nearestPalindromic(n: String): String {
            val num = n.toBigInteger()

            fun BigInteger.find(higher: Boolean): BigInteger {
                val str = this.toString()

                if (str == str.reversed()) {
                    return str.toBigInteger()
                }

                if (!higher && str.trimEnd('0') == "1") {
                    return "9".repeat(str.length - 1).ifEmpty { "0" }.toBigInteger()
                }

                val firstHalf = str.substring(0, (str.length + 1) / 2)
                val secondHalf = str.substring((str.length + 1) / 2)

                val midCount = if (str.length % 2 == 1) 1 else 0

                val firstHalfReversed = firstHalf.dropLast(midCount).reversed()

                return when {
                    firstHalfReversed > secondHalf && higher || firstHalfReversed < secondHalf && !higher -> {
                        (firstHalf + firstHalfReversed).toBigInteger()
                    }

                    else -> {
                        val add = if (higher) BigInteger.ONE else BigInteger.ONE.negate()
                        val newFirstHalf = (firstHalf.toBigInteger() + add).toString()

                        (newFirstHalf + newFirstHalf.dropLast(midCount).reversed()).toBigInteger()
                    }
                }
            }

            val r = if (n == n.reversed()) BigInteger.ONE else BigInteger.ZERO

            val higher = (num + r).find(true)
            val lower = (num - r).find(false)

            return if (higher - num < num - lower) {
                higher
            } else {
                lower
            }.toString()
        }
    }

    expect {
        Solution().nearestPalindromic(
            "2"
        )

    }
}