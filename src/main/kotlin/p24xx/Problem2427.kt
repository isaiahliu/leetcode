package p24xx

import util.expect

fun main() {
    class Solution {
        fun commonFactors(a: Int, b: Int): Int {
            return (1..a.coerceAtMost(b)).count {
                a % it == 0 && b % it == 0
            }
        }
    }

    expect {
        Solution().commonFactors(
            5, 10
        )
    }
}