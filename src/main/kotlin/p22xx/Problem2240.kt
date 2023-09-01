package p22xx

import util.expect

fun main() {
    class Solution {
        fun waysToBuyPensPencils(total: Int, cost1: Int, cost2: Int): Long {
            var sum1 = 0

            var result = 0L
            while (sum1 <= total) {
                result += (total - sum1) / cost2 + 1
                sum1 += cost1
            }
            return result
        }
    }

    expect {
        Solution().waysToBuyPensPencils(
            20, 10, 5
        )
    }
}