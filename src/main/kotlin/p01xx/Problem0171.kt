package p01xx

import util.expect

fun main() {
    class Solution {
        fun titleToNumber(columnTitle: String): Int {
            var result = 0

            columnTitle.forEach {
                result = result * 26 + (it - 'A') + 1
            }

            return result
        }
    }

    expect {
        Solution().titleToNumber("ZY")
    }
}

