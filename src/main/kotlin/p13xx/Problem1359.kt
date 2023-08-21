package p13xx

import util.expect

fun main() {
    class Solution {
        val m = 1000000007

        fun countOrders(n: Int): Int {
            if (n == 1) {
                return 1
            }

            return (n.toLong() * countOrders(n - 1) * ((n - 1) * 2 + 1) % m).toInt()
        }
    }

    expect {
        Solution().countOrders(
            2
        )
    }
}

