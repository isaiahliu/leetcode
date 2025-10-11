package p35xx

import util.expect

fun main() {
    class Solution {
        fun quickmul(x: Long, y: Long, mod: Long): Long {
            var y = y
            var res: Long = 1
            var cur = x % mod
            while (y > 0) {
                if ((y and 1L) == 1L) {
                    res = res * cur % mod
                }
                y = y shr 1
                cur = cur * cur % mod
            }
            return res
        }

        fun magicalSum(m: Int, k: Int, nums: IntArray): Int {
            val n = nums.size
            val mod: Long = 1000000007
            val fac = LongArray(m + 1)
            fac[0] = 1
            for (i in 1..m) {
                fac[i] = fac[i - 1] * i % mod
            }
            val ifac = LongArray(m + 1)
            ifac[0] = 1
            ifac[1] = 1
            for (i in 2..m) {
                ifac[i] = quickmul(i.toLong(), mod - 2, mod)
            }
            for (i in 2..m) {
                ifac[i] = ifac[i - 1] * ifac[i] % mod
            }
            val numsPower = Array(n) { LongArray(m + 1) }
            for (i in 0..<n) {
                numsPower[i][0] = 1
                for (j in 1..m) {
                    numsPower[i][j] = numsPower[i][j - 1] * nums[i] % mod
                }
            }
            val f = Array(n) { Array(m + 1) { Array(m * 2 + 1) { LongArray(k + 1) } } }
            for (j in 0..m) {
                f[0][j][j][0] = numsPower[0][j] * ifac[j] % mod
            }
            var i = 0
            while (i + 1 < n) {
                for (j in 0..m) {
                    for (p in 0..m * 2) {
                        for (q in 0..k) {
                            val q2 = p % 2 + q
                            if (q2 > k) {
                                break
                            }
                            var r = 0
                            while (r + j <= m) {
                                val p2 = p / 2 + r
                                f[i + 1][j + r][p2][q2] += f[i][j][p][q] * numsPower[i + 1][r] % mod * ifac[r] % mod
                                f[i + 1][j + r][p2][q2] %= mod
                                r++
                            }
                        }
                    }
                }
                i++
            }
            var res: Long = 0
            for (p in 0..m * 2) {
                for (q in 0..k) {
                    if (Integer.bitCount(p) + q == k) {
                        res = (res + f[n - 1][m][p][q] * fac[m] % mod) % mod
                    }
                }
            }
            return res.toInt()
        }
    }


    expect {
        Solution().magicalSum(
            8, 2, intArrayOf(4, 9, 30, 44)
        )
    }
}

