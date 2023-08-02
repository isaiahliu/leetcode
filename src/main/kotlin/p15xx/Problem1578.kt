package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minCost(colors: String, neededTime: IntArray): Int {
            var result = 0

            for (i in 1 until colors.length) {
                if (colors[i] == colors[i - 1]) {
                    result += neededTime[i - 1].coerceAtMost(neededTime[i])
                    neededTime[i] = neededTime[i - 1].coerceAtLeast(neededTime[i])
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minCost(
            "abaac", intArrayOf(1, 2, 3, 4, 5)
        ).also { println(it) }
    }
}

