package p18xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxIceCream(costs: IntArray, coins: Int): Int {
            costs.sort()

            var sum = 0
            costs.forEachIndexed { index, cost ->
                sum += cost

                if (sum > coins) {
                    return index
                }
            }

            return costs.size
        }
    }

    measureTimeMillis {
        Solution().maxIceCream(
            intArrayOf(), 1
        ).also { println("${it} should be $it") }

    }.also { println("Time cost: ${it}ms") }
}
