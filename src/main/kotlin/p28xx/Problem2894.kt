package p28xx

import util.expect

fun main() {
    class Solution {
        fun differenceOfSums(n: Int, m: Int): Int {
            return (n / m).let { mCount ->
                (1 + n) * n / 2 - m * (1 + mCount) * mCount
            }
        }
    }

    expect {
        Solution().differenceOfSums(
            1, 2
        )
    }
}
