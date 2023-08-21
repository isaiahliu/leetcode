package p16xx

import util.expect

fun main() {
    class Solution {
        fun minimumOneBitOperations(n: Int): Int {
            var t = n

            var result = 0

            while (t > 0) {
                result = result xor t
                t /= 2
            }

            return result
        }
    }

    expect {
        Solution().minimumOneBitOperations(
            6
        )
    }
}

