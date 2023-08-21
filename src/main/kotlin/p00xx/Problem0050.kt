package p00xx

import util.expect

fun main() {
    class Solution {
        fun myPow(x: Double, n: Int): Double {
            if (x == 1.0) {
                return 1.0
            }

            if (x == -1.0) {
                return if (n % 2 == 1) -1.0 else 1.0
            }

            var result = 1.0

            if (n < 0) {
                for (i in -1 downTo n) {
                    result /= x

                    if (result == 0.0) {
                        break
                    }
                }
            } else {
                for (i in 0 until n) {
                    result *= x
                }
            }

            return result
        }
    }

    expect {
        Solution().myPow(2.0, Int.MIN_VALUE)
    }
}


