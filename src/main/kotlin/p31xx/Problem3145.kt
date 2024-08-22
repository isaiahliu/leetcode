package p31xx

import util.expect

fun main() {
    class Solution {
        fun findProductsOfElements(queries: Array<LongArray>): IntArray {
            val ans = IntArray(queries.size)

            for (i in queries.indices) {
                queries[i][0]++
                queries[i][1]++
                val l = midCheck(queries[i][0])
                val r = midCheck(queries[i][1])
                val mod = queries[i][2].toInt()

                var res: Long = 1
                var pre = countOne(l - 1)
                for (j in 0..59) {
                    if ((1L shl j and l) != 0L) {
                        pre++
                        if (pre >= queries[i][0] && pre <= queries[i][1]) {
                            res = res * (1L shl j) % mod
                        }
                    }
                }

                if (r > l) {
                    var bac = countOne(r - 1)
                    for (j in 0..59) {
                        if ((1L shl j and r) != 0L) {
                            bac++
                            if (bac >= queries[i][0] && bac <= queries[i][1]) {
                                res = res * (1L shl j) % mod
                            }
                        }
                    }
                }

                if (r - l > 1) {
                    val xs = countPow(r - 1) - countPow(l)
                    res = res * powMod(2L, xs, mod) % mod
                }
                ans[i] = res.toInt()
            }

            return ans
        }

        fun midCheck(x: Long): Long {
            var l: Long = 1
            var r = 1e15.toLong()
            while (l < r) {
                val mid = (l + r) shr 1
                if (countOne(mid) >= x) {
                    r = mid
                } else {
                    l = mid + 1
                }
            }
            return r
        }

        fun countOne(x: Long): Long {
            var res: Long = 0
            var sum = 0

            for (i in 60 downTo 0) {
                if ((1L shl i and x) != 0L) {
                    res += 1L * sum * (1L shl i)
                    sum += 1

                    if (i > 0) {
                        res += 1L * i * (1L shl (i - 1))
                    }
                }
            }
            res += sum.toLong()
            return res
        }

        fun countPow(x: Long): Long {
            var res: Long = 0
            var sum = 0

            for (i in 60 downTo 0) {
                if ((1L shl i and x) != 0L) {
                    res += 1L * sum * (1L shl i)
                    sum += i

                    if (i > 0) {
                        res += 1L * i * (i - 1) / 2 * (1L shl (i - 1))
                    }
                }
            }
            res += sum.toLong()
            return res
        }

        fun powMod(x: Long, y: Long, mod: Int): Int {
            var x = x
            var y = y
            var res: Long = 1
            while (y != 0L) {
                if ((y and 1L) != 0L) {
                    res = res * x % mod
                }
                x = x * x % mod
                y = y shr 1
            }
            return res.toInt()
        }
    }

    expect {
        Solution().findProductsOfElements(
            arrayOf(
                longArrayOf(1, 3, 3)
            )
        )
    }
}
