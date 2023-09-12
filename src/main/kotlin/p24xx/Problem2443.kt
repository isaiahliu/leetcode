package p24xx

import util.expect

fun main() {
    class Solution {
        fun sumOfNumberAndReverse(num: Int): Boolean {
            fun Int.reverse(): Int {
                var result = 0
                var t = this
                while (t > 0) {
                    result *= 10
                    result += t % 10
                    t /= 10
                }

                return result
            }

            var n = 0
            while (true) {
                val reverseN = n.reverse()

                when {
                    n + reverseN == num -> return true
                    n > num -> return false
                    else -> n++
                }
            }
        }
    }

    expect {
        Solution().sumOfNumberAndReverse(
            5
        )
    }
}