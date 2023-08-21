package p13xx

import util.expect

fun main() {
    class Solution {
        fun generateTheString(n: Int): String {
            return if (n % 2 == 0) {
                "a".repeat(n - 1) + "b".repeat(1)
            } else {
                "a".repeat(n)
            }
        }
    }

    expect {
        Solution().generateTheString(
            5
        )
    }
}

