package p07xx

import util.expect

fun main() {
    class Solution {
        fun rotatedDigits(n: Int): Int {
            val good = intArrayOf(2, 5, 6, 9)
            val bad = intArrayOf(3, 4, 7)
            return (1..n).count {
                var r = false

                var t = it
                while (t > 0) {
                    when (t % 10) {
                        in good -> r = true
                        in bad -> return@count false
                    }

                    t /= 10
                }

                r
            }
        }
    }

    expect {
        Solution().rotatedDigits(
            10
        )
    }
}