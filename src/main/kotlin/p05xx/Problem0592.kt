package p05xx

import java.math.BigInteger
import util.expect

fun main() {
    class Solution {
        fun fractionAddition(expression: String): String {
            operator fun Pair<BigInteger, BigInteger>.plus(target: Pair<BigInteger, BigInteger>): Pair<BigInteger, BigInteger> {
                val (f1, s1) = this
                val (f2, s2) = target

                val gcd = s1.gcd(s2)

                val newS = s1 * s2 / gcd
                val newF = f1 * s2 / gcd + f2 * s1 / gcd

                return if (newS.compareTo(BigInteger.ZERO) == 0) {
                    BigInteger.ZERO to BigInteger.ONE
                } else {
                    val newGcd = newS.gcd(newF)

                    newF / newGcd to newS / newGcd
                }
            }

            var result = BigInteger.ZERO to BigInteger.ONE

            var first = 0
            var current = 0
            var negate = false
            ("$expression+").forEach {
                when (it) {
                    in '0'..'9' -> {
                        current *= 10
                        current += it - '0'
                    }

                    '/' -> {
                        first = current
                        if (negate) {
                            first = 0 - first
                        }
                        current = 0
                    }

                    '+', '-' -> {
                        if (current > 0) {
                            result += first.toBigInteger() to current.toBigInteger()
                            current = 0
                        }

                        negate = it == '-'
                    }
                }
            }

            return result.let { (f, s) -> "${f}/${s}" }
        }
    }

    expect {
        Solution().fractionAddition("-1/2+22/4-33/5")

    }
}