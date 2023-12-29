package p27xx

import util.expect

fun main() {
    class Solution {
        fun buyChoco(prices: IntArray, money: Int): Int {
            return prices.fold(Int.MAX_VALUE to Int.MAX_VALUE) { (min1, min2), price ->
                when {
                    price < min1 -> price to min1
                    price < min2 -> min1 to price
                    else -> min1 to min2
                }
            }.let { (min1, min2) -> money - min1 - min2 }.takeIf { it >= 0 } ?: money
        }
    }

    expect {
        Solution().buyChoco(
            intArrayOf(), 3
        )
    }
}
