package p25xx

import util.expect

fun main() {
    class Solution {
        fun minimumPossibleSum(n: Int, target: Int): Int {
            val m = 1000000007

            var result = 0L

            val firstPartSize = minOf(target / 2, n).toLong()

            result += firstPartSize * (firstPartSize + 1) / 2
            result %= m

            val remainingPart = n - firstPartSize
            result += (target + target + remainingPart - 1) * remainingPart / 2
            result %= m

            return result.toInt()
        }
    }

    expect {
        Solution().minimumPossibleSum(
            3, 3
        )
    }
}