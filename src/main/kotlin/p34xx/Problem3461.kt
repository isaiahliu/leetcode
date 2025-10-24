package p34xx

import util.expect

fun main() {
    class Solution {
        private fun init() {
            if (initialized) {
                return
            }
            initialized = true

            invF[0] = 1
            f[0] = invF[0]
            for (i in 1..MX) {
                var x = i
                val e2 = Integer.numberOfTrailingZeros(x)
                x = x shr e2
                var e5 = 0
                while (x % 5 == 0) {
                    e5++
                    x /= 5
                }
                f[i] = f[i - 1] * x % MOD
                invF[i] = pow(f[i], 3) // 欧拉定理求逆元
                p2[i] = p2[i - 1] + e2
                p5[i] = p5[i - 1] + e5
            }
        }

        private fun comb(n: Int, k: Int): Int {
            val e2 = p2[n] - p2[k] - p2[n - k]
            return f[n] * invF[k] * invF[n - k] *
                    (if (e2 > 0) POW2[(e2 - 1) % 4] else 1) *
                    (if (p5[n] - p5[k] - p5[n - k] > 0) 5 else 1) % MOD
        }

        fun hasSameDigits(S: String): Boolean {
            init()
            val s = S.toCharArray()
            var diff = 0
            for (i in 0..<s.size - 1) {
                diff += comb(s.size - 2, i) * (s[i].code - s[i + 1].code)
            }
            return diff % MOD == 0
        }

        private val MOD = 10
        private val MX = 100000
        private val POW2 = intArrayOf(2, 4, 8, 6)

        private val f = IntArray(MX + 1)
        private val invF = IntArray(MX + 1)
        private val p2 = IntArray(MX + 1)
        private val p5 = IntArray(MX + 1)

        private var initialized = false

        private fun pow(x: Int, n: Int): Int {
            var x = x
            var n = n
            var res = 1
            while (n > 0) {
                if (n % 2 > 0) {
                    res = res * x % MOD
                }
                x = x * x % MOD
                n /= 2
            }
            return res
        }
    }

    expect {
        Solution().hasSameDigits(
            "2421"
        )
    }
}
