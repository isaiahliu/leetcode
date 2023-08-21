package p13xx

import util.expect

fun main() {
    class Solution {
        fun sumZero(n: Int): IntArray {
            return IntArray(n) {
                when {
                    n % 2 > 0 -> it - n / 2
                    it % 2 == 0 -> it / 2 + 1
                    else -> -it / 2 - 1
                }
            }
        }
    }

    expect {
        Solution().sumZero(
            5
        )
    }
}

