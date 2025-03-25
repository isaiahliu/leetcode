package p28xx

import util.expect

fun main() {
    class Solution {
        fun minimumSum(n: Int, k: Int): Int {
            var firstPart = minOf(n, k / 2)

            var result = (1 + firstPart) * firstPart / 2

            var remainingPart = n - firstPart

            if (remainingPart > 0) {
                result += (k * 2 + remainingPart - 1) * remainingPart / 2
            }

            return result
        }
    }
    expect {
        Solution().minimumSum(
            5, 4
        )
    }
}
