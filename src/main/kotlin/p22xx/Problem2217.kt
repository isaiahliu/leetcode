package p22xx

import util.expect

fun main() {
    class Solution {
        fun kthPalindrome(queries: IntArray, intLength: Int): LongArray {
            val addition = intLength % 2

            val half = intLength / 2
            val leftMin = "1${"0".repeat(half - 1 + addition)}".toInt()
            val leftMax = "9".repeat(half + addition).toInt()

            return queries.map {
                val left = leftMin + it - 1
                if (left <= leftMax) {
                    "${left}${left.toString().reversed().drop(addition)}".toLong()
                } else {
                    -1L
                }
            }.toLongArray()
        }
    }

    expect {
        Solution().kthPalindrome(
            intArrayOf(1, 2, 3, 4, 5, 90), 3
        ).toList()
    }
}