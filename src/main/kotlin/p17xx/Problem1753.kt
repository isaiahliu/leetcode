package p17xx

import util.expect

fun main() {
    class Solution {
        fun maximumScore(a: Int, b: Int, c: Int): Int {
            return (a + b).coerceAtMost(b + c).coerceAtMost(a + c).coerceAtMost((a + b + c) / 2)
        }
    }

    expect {
        Solution().maximumScore(
            1, 2, 3
        )
    }
}
