package p05xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findRotateSteps(ring: String, key: String): Int {
            val cache = hashMapOf<Pair<Int, Int>, Int>()

            val charIndices = ring.withIndex().groupBy({ it.value }, { it.index })

            val dp = Array(key.length) {
                hashMapOf<Int, Int>()
            }

            charIndices[key[0]]?.forEach { i ->
                dp[0][i] = i.coerceAtMost(ring.length - i)
            }

            for (i in 1 until key.length) {
                val c = key[i]

                charIndices[c]?.forEach { currentIndex ->
                    var minStep = Int.MAX_VALUE
                    charIndices[key[i - 1]]?.forEach { preIndex ->
                        minStep = minStep.coerceAtMost(
                            (dp[i - 1][preIndex]
                                ?: 0) + ((ring.length + currentIndex - preIndex) % ring.length).coerceAtMost((ring.length + preIndex - currentIndex) % ring.length)
                        )
                    }

                    dp[i][currentIndex] = minStep
                }
            }
            return dp[dp.lastIndex].values.min() + key.length
        }
    }

    measureTimeMillis {
        Solution().findRotateSteps(
            "godding", "gd"
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}