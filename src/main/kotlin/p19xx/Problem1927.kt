package p19xx

import util.expect

fun main() {
    class Solution {
        fun sumGame(num: String): Boolean {
            val sums = intArrayOf(0, 0)
            val ops = intArrayOf(0, 0)

            num.forEachIndexed { index, c ->
                when (c) {
                    '?' -> ops[index / (num.length / 2)]++
                    else -> sums[index / (num.length / 2)] += c - '0'
                }
            }

            val deltaSum = sums[1] - sums[0]
            val deltaOp = ops[1] - ops[0]

            val sign = deltaSum * deltaOp
            return when {
                sign > 0 -> true
                sign == 0 -> deltaSum != 0 || deltaOp != 0
                deltaOp % 2 != 0 -> true
                else -> deltaSum != deltaOp / 2 * 9 * -1
            }
        }
    }

    expect {
        Solution().sumGame(
            "97810420??849??74?3?3261?7?283883293141?91"
        )
    }
}