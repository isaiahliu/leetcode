package p31xx

import util.expect

fun main() {
    class Solution {
        fun maxBottlesDrunk(numBottles: Int, numExchange: Int): Int {
            var result = numBottles

            var exchange = numExchange
            var empty = numBottles
            while (empty >= exchange) {
                result++
                empty -= exchange
                empty++
                exchange++
            }

            return result
        }
    }

    expect {
        Solution().maxBottlesDrunk(
            1, 2
        )
    }
}
