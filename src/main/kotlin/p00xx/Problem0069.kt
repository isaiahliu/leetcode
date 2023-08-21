package p00xx

import util.expect

fun main() {
    class Solution {
        fun mySqrt(x: Int): Int {
            var base = 10000

            var current = 0L

            while (base > 0) {
                while (current * current <= x) {
                    current += base
                }

                current -= base

                base /= 10
            }

            return current.toInt()
        }
    }

    expect {
        Solution().mySqrt(8)
    }
}

