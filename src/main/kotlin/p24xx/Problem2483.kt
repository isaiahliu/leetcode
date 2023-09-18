package p24xx

import util.expect

fun main() {
    class Solution {
        fun bestClosingTime(customers: String): Int {
            val leftIdle = IntArray(customers.length + 1)
            val rightBusy = IntArray(customers.length + 1)

            var l = 0
            var r = 0

            for (i in customers.indices) {
                leftIdle[i] = l
                if (customers[i] == 'N') {
                    l++
                }

                if (customers[customers.lastIndex - i] == 'Y') {
                    r++
                }

                rightBusy[customers.lastIndex - i] = r
            }

            leftIdle[leftIdle.lastIndex] = l

            return leftIdle.indices.minBy { leftIdle[it] + rightBusy[it] }
        }
    }

    expect {
        Solution().bestClosingTime(
            "YYYY"
        )
    }
}
