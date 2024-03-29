package p29xx

import util.expect

fun main() {
    class Solution {
        fun minimumAddedCoins(coins: IntArray, target: Int): Int {
            coins.sort()

            var result = 0

            var num = 1
            var coinIndex = 0
            while (num <= target) {
                if (coinIndex < coins.size && coins[coinIndex] <= num) {
                    num += coins[coinIndex++]
                } else {
                    num *= 2
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumAddedCoins(
            intArrayOf(7, 12, 9, 8, 9, 15), 4
        )
    }
}
