package p18xx

import util.expect

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

    expect {
        Solution().maxIceCream(
            intArrayOf(), 1
        )

    }
}
