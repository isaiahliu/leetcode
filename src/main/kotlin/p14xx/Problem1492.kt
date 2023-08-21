package p14xx

import util.expect

fun main() {
    class Solution {
        fun kthFactor(n: Int, k: Int): Int {
            var count = 0

            repeat(n) {
                if (n % (it + 1) == 0) {
                    count++
                }

                if (count == k) {
                    return it + 1
                }
            }

            return -1
        }
    }

    expect {
        Solution().kthFactor(
            7, 2
        )

    }
}

