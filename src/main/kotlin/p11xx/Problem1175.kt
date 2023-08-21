package p11xx

import util.expect

fun main() {
    class Solution {
        fun numPrimeArrangements(n: Int): Int {
            fun Int.isPrime(): Boolean {
                when {
                    this == 1 -> {
                        return false
                    }

                    this == 2 -> {
                        return true
                    }

                    this % 2 == 0 -> {
                        return false
                    }

                    else -> {
                        for (i in 3 until this) {
                            if (i * i > this) {
                                break
                            }

                            if (this % i == 0) {
                                return false
                            }
                        }

                        return true
                    }
                }
            }

            val primeCount = (1..n).count { it.isPrime() }

            var result = 1L
            val m = 1000000007

            repeat(primeCount) {
                result *= (it + 1)
                result %= m
            }

            repeat(n - primeCount) {
                result *= (it + 1)
                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().numPrimeArrangements(
            5
        )
    }
}