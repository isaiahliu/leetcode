package p03xx

import util.expect

fun main() {
    class Solution {
        fun lastRemaining(n: Int): Int {
            var start = 1
            var interval = 1
            var count = n
            var direction = 0

            while (count > 1) {
                if (direction == 0 || count % 2 == 1) {
                    start += interval
                }

                interval *= 2
                count /= 2
                direction = 1 - direction
            }

            return start
        }
    }

    expect {
        Solution().lastRemaining(
            9
        )
    }
}

