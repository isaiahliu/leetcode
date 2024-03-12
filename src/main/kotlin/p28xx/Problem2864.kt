package p28xx

import util.expect

fun main() {
    class Solution {
        fun maximumOddBinaryNumber(s: String): String {
            return s.count { it == '1' }.let { "${"1".repeat(it - 1)}${"0".repeat(s.length - it)}1" }
        }
    }

    expect {
        Solution().maximumOddBinaryNumber(
            "1"
        )
    }
}
