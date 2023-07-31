package p15xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().numWaterBottles(
            5, 2
        ).also { println(it) }
    }
}

