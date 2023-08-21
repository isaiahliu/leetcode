package p13xx

import util.expect

fun main() {
    class Solution {
        fun minFlips(a: Int, b: Int, c: Int): Int {
            var result = 0

            var ta = a
            var tb = b
            var tc = c

            while (ta > 0 || tb > 0 || tc > 0) {
                val lastA = ta and 1
                val lastB = tb and 1

                when (tc and 1) {
                    lastA or lastB -> {}

                    1 -> {
                        result++
                    }

                    else -> {
                        result += lastA + lastB
                    }
                }

                ta /= 2
                tb /= 2
                tc /= 2
            }

            return result
        }
    }

    expect {
        Solution().minFlips(
            1, 2, 3
        )
    }
}

