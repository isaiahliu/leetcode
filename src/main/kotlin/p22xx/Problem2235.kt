package p22xx

import util.expect

fun main() {
    class Solution {
        fun sum(num1: Int, num2: Int): Int {
            return num1 + num2
        }
    }

    expect {
        Solution().sum(
            1, 1
        )
    }
}