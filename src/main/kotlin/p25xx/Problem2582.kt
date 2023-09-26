package p25xx

import util.expect

fun main() {
    class Solution {
        fun passThePillow(n: Int, time: Int): Int {
            var rem = time % (n - 1)

            if (time / (n - 1) % 2 == 1) {
                rem = n - rem - 1
            }

            return rem + 1
        }
    }

    expect {
        Solution().passThePillow(
            5, 6
        )
    }
}