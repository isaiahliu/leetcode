package p13xx

import util.expect

fun main() {
    class Solution {
        fun numberOfSteps(num: Int): Int {
            return when {
                num == 0 -> 0
                num % 2 == 0 -> numberOfSteps(num / 2) + 1
                else -> numberOfSteps(num - 1) + 1
            }
        }
    }

    expect {
        Solution().numberOfSteps(
            14
        )
    }
}

