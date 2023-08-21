package p03xx

import util.expect

fun main() {
    class Solution {
        fun countNumbersWithUniqueDigits(n: Int): Int {
            if (n == 0) {
                return 1
            }

            var t = 9

            var result = 9

            repeat(n - 1) {
                result *= t--
            }

            result += countNumbersWithUniqueDigits(n - 1)

            return result
        }
    }

    expect {
        Solution().countNumbersWithUniqueDigits(
            2
        )
    }
}

