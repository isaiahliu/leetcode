package p32xx

import util.expect

fun main() {
    class Solution {
        fun generateKey(num1: Int, num2: Int, num3: Int): Int {
            var nums = intArrayOf(num1, num2, num3)
            var b = 1
            var result = 0

            repeat(4) {
                result += b * nums.minOf {
                    (it % 10).also {
                        it / 10
                    }
                }
                b *= 10
            }

            return result
        }
    }

    expect {
        Solution().generateKey(
            1, 2, 3
        )
    }
}
