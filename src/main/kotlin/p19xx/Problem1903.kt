package p19xx

import util.expect

fun main() {
    class Solution {
        fun largestOddNumber(num: String): String {
            for (i in num.lastIndex downTo 0) {
                if ((num[i] - '0') % 2 == 1) {
                    return num.take(i + 1)
                }
            }

            return ""
        }
    }

    expect {
        Solution().largestOddNumber(
            ""
        ).toList()
    }
}
