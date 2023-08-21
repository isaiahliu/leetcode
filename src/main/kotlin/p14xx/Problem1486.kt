package p14xx

import util.expect

fun main() {
    class Solution {
        fun xorOperation(n: Int, start: Int): Int {
            var result = 0

            repeat(n) {
                result = result xor (start + it * 2)
            }

            return result
        }
    }

    expect {
        Solution().xorOperation(
            5, 1
        )

    }
}

