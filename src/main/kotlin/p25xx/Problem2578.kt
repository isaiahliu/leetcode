package p25xx

import util.expect

fun main() {
    class Solution {
        fun splitNum(num: Int): Int {
            var base = 1

            return num.toString().toCharArray().sortedDescending().foldIndexed(0) { index, sum, c ->
                (sum + (c - '0') * base).also {
                    base *= (index % 2) * 9 + 1
                }
            }
        }
    }

    expect {
        Solution().splitNum(
            5
        )
    }
}

