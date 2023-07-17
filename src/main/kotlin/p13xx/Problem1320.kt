package p13xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minimumDistance(word: String): Int {
            val pos = Array(26) {
                it / 6 to it % 6
            }

            fun Char.distance(target: Char?): Int {
                val (r1, c1) = pos[this - 'A']
                val (r2, c2) = pos[(target ?: return 0) - 'A']

                return (r1 - r2).absoluteValue + (c1 - c2).absoluteValue
            }

            val cache = hashMapOf<Pair<Pair<Char, Char?>, Int>, Int>()

            fun dfs(left: Char, right: Char?, index: Int): Int {
                if (index == word.length) {
                    return 0
                }

                val cacheKey = left to right to index
                if (cacheKey in cache) {
                    return cache[cacheKey] ?: 0
                }

                val c = word[index]

                val result = (c.distance(left) + dfs(c, right, index + 1)).coerceAtMost(
                    c.distance(right) + dfs(left, c, index + 1)
                )

                cache[cacheKey] = result
                return result
            }

            return dfs(word[0], null, 1)
        }
    }

    measureTimeMillis {
        Solution().minimumDistance(
            "HAPPY"
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}

