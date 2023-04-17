package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun soupServings(n: Int): Double {
            if (n > 5000) {
                return 1.0
            }

            val cache = hashMapOf<Pair<Int, Int>, Double>()

            fun process(a: Int, b: Int): Double {
                return when {
                    a <= 0 && b <= 0 -> 0.5
                    a <= 0 -> 1.0
                    b > 0 -> {
                        val cacheKey = a to b
                        if (cacheKey in cache) {
                            return cache[cacheKey] ?: 0.0
                        }
                        ((process(a - 100, b) + process(a - 75, b - 25) + process(
                            a - 50, b - 50
                        ) + process(a - 25, b - 75)) / 4).also {
                            cache[cacheKey] = it
                        }
                    }

                    else -> 0.0
                }
            }

            return process(n, n)
        }
    }

    measureTimeMillis {
        Solution().soupServings(
            660295675
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}