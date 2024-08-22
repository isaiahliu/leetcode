package p31xx

import util.expect

fun main() {
    class Solution {
        fun minEnd(n: Int, x: Int): Long {
            var num1 = x
            var num2 = n - 1

            var result = 0L
            var base = 1L

            while (num1 > 0) {
                result += base * ((num1 % 2).takeIf { it == 1 } ?: run {
                    (num2 % 2).also { num2 /= 2 }
                })

                num1 /= 2
                base *= 2
            }

            while (num2 > 0) {
                result += base * (num2 % 2)
                num2 /= 2
                base *= 2
            }

            return result
        }
    }

    expect {
        Solution().minEnd(
            3, 4
        )
    }
}
