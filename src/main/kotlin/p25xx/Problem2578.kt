package p25xx

import util.expect

fun main() {
    class Solution {
        fun splitNum(num: Int): Int {
            var result = 0
            var base = 1

            num.toString().toCharArray().sortedDescending().forEachIndexed { index, c ->
                result += (c - '0') * base

                base *= (index % 2) * 9 + 1
            }

            return result
        }
    }

    expect {
        Solution().splitNum(
            5
        )
    }
}

