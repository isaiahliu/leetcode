package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMaxGridHappiness(m: Int, n: Int, introvertsCount: Int, extrovertsCount: Int): Int {
            val score = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, -60, -10), intArrayOf(0, -10, 40))

            var t = 1
            val p = IntArray(5) {
                t.also { t *= 3 }
            }

            val cache: Array<Array<Array<IntArray>>> = Array(25) {
                Array(243) {
                    Array(7) {
                        IntArray(7) { -1 }
                    }
                }
            }

            fun dfs(pos: Int, status: Int, iv: Int, ev: Int): Int {
                if (pos == n * m || iv == 0 && ev == 0) {
                    return 0
                }
                var res = cache[pos][status][iv][ev]
                if (res != -1) {
                    return res
                }
                res = 0
                val up = status / p[n - 1]
                var left = status % 3
                if (pos % n == 0) {
                    left = 0
                }
                for (i in 0..2) {
                    if (i == 1 && iv == 0 || i == 2 && ev == 0) {
                        continue
                    }
                    val nextMask = status % p[n - 1] * 3 + i
                    var scoreSum = (dfs(pos + 1, nextMask, iv - if (i == 1) 1 else 0, ev - if (i == 2) 1 else 0)
                            + score[up][i] + score[left][i])
                    if (i == 1) {
                        scoreSum += 120
                    } else if (i == 2) {
                        scoreSum += 40
                    }
                    res = res.coerceAtLeast(scoreSum)
                }
                cache[pos][status][iv][ev] = res
                return res
            }
            return dfs(0, 0, introvertsCount, extrovertsCount)
        }
    }

    measureTimeMillis {
        Solution().getMaxGridHappiness(
            5,
            5,
            3,
            6
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}