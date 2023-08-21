package p24xx

import util.expect

fun main() {
    class Solution {
        fun numberOfCuts(n: Int): Int {
            return when {
                n == 1 -> {
                    0
                }

                n % 2 == 1 -> {
                    n
                }

                else -> {
                    n / 2
                }
            }
        }
    }

    expect {
        Solution().numberOfCuts(
            8
        )
    }
}