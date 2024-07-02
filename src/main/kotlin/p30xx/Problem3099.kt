package p30xx

import util.expect

fun main() {
    class Solution {
        fun sumOfTheDigitsOfHarshadNumber(x: Int): Int {
            return x.toString().sumOf { it - '0' }.takeIf { x % it == 0 } ?: -1
        }
    }

    expect {
        Solution().sumOfTheDigitsOfHarshadNumber(
            23
        )
    }
}
