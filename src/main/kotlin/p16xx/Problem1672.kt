package p16xx

import util.expect

fun main() {
    class Solution {
        fun maximumWealth(accounts: Array<IntArray>): Int {
            return accounts.map { it.sum() }.max()
        }
    }

    expect {
        Solution().maximumWealth(
            arrayOf()
        )
    }
}

