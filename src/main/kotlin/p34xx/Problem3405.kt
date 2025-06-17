package p34xx

import util.expect

fun main() {
    class Solution {
        fun countGoodArrays(n: Int, m: Int, k: Int): Int {
            val mod = 1000000007

            fun qpow(x: Int, n: Int): Long {
                var tx = x.toLong()
                var tn = n
                var result = 1L
                while (tn > 0) {
                    if ((tn and 1) == 1) {
                        result = result * tx % mod
                    }
                    tx = tx * tx % mod
                    tn = tn shr 1
                }
                return result
            }

            var t = 1L
            val fact = LongArray(100000) { index ->
                t.also {
                    t = t * (index + 1) % mod
                }
            }

            val invFact = LongArray(100000)
            invFact[invFact.lastIndex] = qpow(fact[invFact.lastIndex].toInt(), mod - 2)
            for (i in invFact.lastIndex downTo 1) {
                invFact[i - 1] = invFact[i] * i % mod
            }

            fun comb(n: Int, m: Int): Long {
                return fact[n] * invFact[m] % mod * invFact[n - m] % mod
            }

            return (comb(n - 1, k) * m % mod * qpow(m - 1, n - k - 1) % mod).toInt()
        }
    }

    expect {
        Solution().countGoodArrays(
            75312, 33935, 50785
        )
    }
}
