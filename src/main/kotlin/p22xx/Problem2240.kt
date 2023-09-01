package p22xx

import util.expect

fun main() {
    class Solution {
        fun waysToBuyPensPencils(total: Int, cost1: Int, cost2: Int): Long {
            var result = 0L
            for (sum1 in 0..total step cost1) {
                result += (total - sum1) / cost2 + 1
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