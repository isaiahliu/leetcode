package p24xx

import util.expect

fun main() {
    class Solution {
        fun minimumMoney(transactions: Array<IntArray>): Long {
            var maxRemain = 0L
            var maxStart = 0L
            var lossSum = 0L

            transactions.forEach { (cost, cashback) ->
                if (cost > cashback) {
                    lossSum += cost - cashback
                    maxRemain = maxRemain.coerceAtLeast(cashback.toLong())
                } else {
                    maxStart = maxStart.coerceAtLeast(cost.toLong())
                }
            }

            return maxStart.coerceAtLeast(maxRemain) + lossSum
        }
    }

    expect {
        Solution().minimumMoney(
            arrayOf()
        )
    }
}