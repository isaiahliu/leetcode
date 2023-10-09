package p25xx

import util.expect

fun main() {
    class Solution {
        fun splitNum(num: Int): Int {
            return num.toString().toCharArray().sortedDescending().foldIndexed(0 to 1) { index, (sum, base), c ->
                sum + (c - '0') * base to base * ((index % 2) * 9 + 1)
            }.first
        }
    }

    expect {
        Solution().splitNum(
            4325
        )
    }
}

