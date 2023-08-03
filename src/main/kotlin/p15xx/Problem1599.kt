package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun minOperationsMaxProfit(customers: IntArray, boardingCost: Int, runningCost: Int): Int {
            var result = -1
            var maxProfit = 0
            var waitingCount = 0
            var profit = 0

            var customerIndex = 0
            while (customerIndex < customers.size || waitingCount > 0) {
                customers.getOrNull(customerIndex++)?.also {
                    waitingCount += it
                }

                val onBoardCustomer = waitingCount.coerceAtMost(4)
                waitingCount -= onBoardCustomer
                profit += onBoardCustomer * boardingCost
                profit -= runningCost

                if (profit > maxProfit) {
                    maxProfit = profit
                    result = customerIndex
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().minOperationsMaxProfit(
            intArrayOf(8, 3), 5, 6
        ).also { println("${it} should be ${it}") }
    }
}

