package p08xx

import util.expect

fun main() {
    class Solution {
        fun consecutiveNumbersSum(n: Int): Int {
            var result = 1

            var d = 2
            while (true) {
                val md2 = n * 2 - d * d - d

                if (md2 < 0) {
                    break
                }

                if (md2 % (d * 2) == 0) {
                    result++
                }

                d++
            }

            return result
        }
    }

    expect {
        Solution().consecutiveNumbersSum(
            15
        )
    }
}