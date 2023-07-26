package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxScore(cardPoints: IntArray, k: Int): Int {
            var sum = 0
            repeat(k) {
                sum += cardPoints[it]
            }

            var result = sum

            repeat(k) {
                sum -= cardPoints[k - it - 1]
                sum += cardPoints[cardPoints.lastIndex - it]

                result = result.coerceAtLeast(sum)
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxScore(
            intArrayOf(), 5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

