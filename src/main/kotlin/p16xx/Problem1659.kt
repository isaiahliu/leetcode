package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getMaxGridHappiness(m: Int, n: Int, introvertsCount: Int, extrovertsCount: Int): Int {
            val INTRO = 1
            val EXTRO = 2
            val EMPTY = 0

            var t = 1
            val bits3 = IntArray(n + 1) {
                t.also { t *= 3 }
            }

            fun Int.bit3At(pos: Int): Int {
                return this / bits3[pos] % 3
            }

            fun Int.replaceBit3At(pos: Int, num: Int): Int {
                val left = this / bits3[pos + 1]
                val right = this % bits3[pos]

                return left * bits3[pos + 1] + num * bits3[pos] + right
            }

            val cache: MutableMap<Pair<Pair<Int, Int>, Pair<Int, Int>>, Int> = hashMapOf()
            fun dfs(pos: Int, status: Int, introCount: Int, extroCount: Int): Int {
                if (pos == m * n || introCount == introvertsCount && extroCount == extrovertsCount) {
                    return 0
                }

                val cacheKey = (pos to status) to (introCount to extroCount)

                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val column = pos % n

                val neighbors = arrayListOf<Int>()

                status.bit3At(column).takeIf { it > 0 }?.also {
                    neighbors.add(it)
                }

                (column - 1).takeIf { it >= 0 }?.let { status.bit3At(it) }?.takeIf { it > 0 }?.also {
                    neighbors.add(it)
                }

                var result = dfs(pos + 1, status.replaceBit3At(column, EMPTY), introCount, extroCount)

                var extraScore = 0
                neighbors.forEach {
                    when (it) {
                        INTRO -> {
                            extraScore -= 30
                        }

                        EXTRO -> {
                            extraScore += 20
                        }
                    }
                }

                if (introCount < introvertsCount) {
                    result = result.coerceAtLeast(
                        120 - neighbors.size * 30 + extraScore + dfs(
                            pos + 1,
                            status.replaceBit3At(column, INTRO),
                            introCount + 1,
                            extroCount
                        )
                    )
                }

                if (extroCount < extrovertsCount) {
                    result = result.coerceAtLeast(
                        40 + neighbors.size * 20 + extraScore + dfs(
                            pos + 1,
                            status.replaceBit3At(column, EXTRO),
                            introCount,
                            extroCount + 1
                        )
                    )
                }

                cache[cacheKey] = result
                return result
            }

            return dfs(0, 0, 0, 0)
        }
    }

    measureTimeMillis {
        Solution().getMaxGridHappiness(
            2,
            3,
            1,
            2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}