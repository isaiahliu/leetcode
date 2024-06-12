package p28xx

import util.expect

fun main() {
    class Solution {
        fun accountBalanceAfterPurchase(purchaseAmount: Int): Int {
            return 100 - (purchaseAmount + 5) / 10 * 10
        }
    }

    expect {
        Solution().accountBalanceAfterPurchase(
            5
        )
    }
}
