package p19xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun colorTheGrid(m: Int, n: Int): Int {
            val mod = 1000000007
            val mask = 0b111
            val colors = intArrayOf(0b001, 0b010, 0b100)

            fun Int.arrange(): Int {
                if (this and mask <= colors[0]) {
                    return this
                }

                var result = 0

                var cur = 0
                var t = this
                var base = 1
                val map = hashMapOf<Int, Int>()
                while (t != 0) {
                    val last = t and mask

                    result += colors[map.computeIfAbsent(last) { cur++ }] * base
                    base = base shl 3
                    t = t shr 3
                }

                return result
            }

            val cache = hashMapOf<Pair<Pair<Int, Int>, Int>, Int>()
            fun dfs(row: Int, index: Int, status: Int): Int {
                if (row == n) {
                    return 1
                }

                if (index == m) {
                    return dfs(row + 1, 0, status)
                }

                val cacheKey = row to index to status
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                var result = 0L

                val existingColors = hashSetOf((status shr index * 3) and mask)

                if (index > 0) {
                    existingColors.add((status shr ((index - 1) * 3)) and mask)
                }

                colors.forEach {
                    if (it !in existingColors) {
                        result += dfs(
                            row,
                            index + 1,
                            ((status and (mask shl (index * 3)).inv()) or (it shl (index * 3))).arrange()
                        )

                        result %= mod
                    }
                }

                cache[cacheKey] = result.toInt()
                return result.toInt()
            }

            return dfs(0, 0, 0)
        }
    }

    measureTimeMillis {
        Solution().colorTheGrid(
            5, 5
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}