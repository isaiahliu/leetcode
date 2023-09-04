package p22xx

import util.expect

fun main() {
    class Solution {
        fun divisorSubstrings(num: Int, k: Int): Int {
            val str = num.toString()
            if (k > str.length) {
                return 0
            }

            var base = 1
            repeat(k - 1) {
                base *= 10
            }

            var result = 0
            var n = str.take(k).toLong()
            if (n > 0 && num % n == 0L) {
                result++
            }

            for (i in k until str.length) {
                n -= (str[i - k] - '0') * base
                n *= 10
                n += str[i] - '0'

                if (n > 0 && num % n == 0L) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().divisorSubstrings(
            430043, 2
        )
    }
}