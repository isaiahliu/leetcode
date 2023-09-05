package p22xx

import util.expect

fun main() {
    class Solution {
        fun digitCount(num: String): Boolean {
            val counts = num.groupingBy { it - '0' }.eachCount()

            num.forEachIndexed { index, c ->
                if (c - '0' != (counts[index] ?: 0)) {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().digitCount(
            ""
        )
    }
}