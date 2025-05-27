package p28xx

import util.expect

fun main() {
    class Solution {
        fun differenceOfSums(n: Int, m: Int): Int {
            return (n / m).let { mCount ->
                (1 + n) * n / 2 - (1 + mCount) * m * mCount
            }
        }
    }

    expect {
        Solution().differenceOfSums(
            1, 2
        )
    }
}
