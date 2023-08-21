package p03xx

import util.expect

fun main() {
    class Solution {
        fun countBits(n: Int): IntArray {
            return IntArray(n + 1) {
                Integer.bitCount(it)
            }
        }
    }

    expect {
        Solution().countBits(
            50
        ).toList()
    }
}

