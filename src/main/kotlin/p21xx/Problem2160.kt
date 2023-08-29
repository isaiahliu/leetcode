package p21xx

import util.expect

fun main() {
    class Solution {
        fun minimumSum(num: Int): Int {
            val (num1, num2, num3, num4) = num.toString().map { it - '0' }.sorted()

            return (num1 + num2) * 10 + num3 + num4
        }
    }

    expect {
        Solution().minimumSum(
            5
        )
    }
}