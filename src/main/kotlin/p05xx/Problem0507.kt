package p05xx

import util.expect

fun main() {
    class Solution {
        fun checkPerfectNumber(num: Int): Boolean {
            if (num == 1) {
                return false
            }

            val factors = hashSetOf(1)

            var factor = 2

            while (factor * factor <= num) {
                if (num % factor == 0) {
                    factors.add(factor)
                    factors.add(num / factor)
                }

                factor++
            }

            return factors.sum() == num
        }
    }

    expect {
        Solution().checkPerfectNumber(
            11
        )
    }
}