package p24xx

import util.expect

fun main() {
    class Solution {
        fun makeIntegerBeautiful(n: Long, target: Int): Long {
            var t = n
            var result = 0L
            var base = 1L

            while (t.toString().sumOf { it - '0' } > target) {
                while (t % 10 == 0L) {
                    base *= 10
                    t /= 10
                }

                (10 - t % 10).also {
                    result += it * base
                    t += it
                }
            }

            return result
        }
    }

    expect {
        Solution().makeIntegerBeautiful(
            615024862295, 7
        )
    }
}