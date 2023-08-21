package p15xx

import util.expect

fun main() {
    class Solution {
        fun numWaterBottles(numBottles: Int, numExchange: Int): Int {
            var result = numBottles

            var empty = numBottles
            while (empty >= numExchange) {
                result += empty / numExchange
                empty = empty % numExchange + empty / numExchange
            }

            return result
        }
    }

    expect {
        Solution().numWaterBottles(
            5, 2
        )
    }
}

