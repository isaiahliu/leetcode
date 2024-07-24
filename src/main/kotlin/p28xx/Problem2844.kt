package p28xx

import util.expect

fun main() {
    class Solution {
        fun minimumOperations(num: String): Int {
            var hasFive = false
            var hasZero = false

            for ((index, c) in num.reversed().withIndex()) {
                val n = c - '0'

                if (hasFive && n % 5 == 2 || hasZero && n % 5 == 0) {
                    return index - 1
                }

                hasFive = hasFive || c == '5'
                hasZero = hasZero || c == '0'
            }

            return num.length - if (hasZero) 1 else 0
        }
    }

    expect {
        Solution().minimumOperations(
            ""
        )
    }
}
