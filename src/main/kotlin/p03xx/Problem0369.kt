package p03xx

import util.expect

fun main() {
    class Solution {
        fun getSum(a: Int, b: Int): Int {
            val x = a xor b
            val y = a and b

            return if (y == 0) {
                x
            } else {
                getSum(x, y shl 1)
            }
        }
    }

    expect {
        Solution().getSum(
            1, 2
        )
    }
}

