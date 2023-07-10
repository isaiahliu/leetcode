package p11xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun numEquivDominoPairs(dominoes: Array<IntArray>): Int {
            var result = 0

            val map = hashMapOf<Pair<Int, Int>, Int>()
            dominoes.forEach { (a, b) ->
                val d = a.coerceAtMost(b) to a.coerceAtLeast(b)

                val r = map[d] ?: 0

                result += r
                map[d] = r + 1
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().numEquivDominoPairs(
            arrayOf()
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}