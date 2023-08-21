package p25xx

import util.expect

fun main() {
    class Solution {
        fun alternateDigitSum(n: Int): Int {
            var t = n
            var result = 0

            while (t > 0) {
                result = t % 10 - result

                t /= 10
            }

            return result
        }
    }

    expect {
        Solution().alternateDigitSum(
            12345
        )
    }
}
