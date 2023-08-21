package p19xx

import util.expect

fun main() {
    class Solution {
        fun countTriples(n: Int): Int {
            val squares = (1..n).map { it * it }.toSet()

            var result = 0

            for (num1 in 1..n) {
                for (num2 in num1 + 1..n) {
                    if (num1 * num1 + num2 * num2 in squares) {
                        result += 2
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countTriples(
            25
        )
    }
}