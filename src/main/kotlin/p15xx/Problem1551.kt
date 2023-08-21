package p15xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(n: Int): Int {
            return n * n / 4
        }
    }

    expect {
        Solution().minOperations(
            5
        )
    }
}

