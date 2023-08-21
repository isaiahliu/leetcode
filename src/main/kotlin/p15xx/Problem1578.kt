package p15xx

import util.expect

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

    expect {
        Solution().minCost(
            "abaac", intArrayOf(1, 2, 3, 4, 5)
        )
    }
}

