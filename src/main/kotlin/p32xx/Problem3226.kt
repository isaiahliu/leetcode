package p32xx

import util.expect

fun main() {
    class Solution {
        fun minChanges(n: Int, k: Int): Int {
            return (n - k).takeIf { it and k == 0 }?.countOneBits() ?: -1
        }
    }

    expect {
        Solution().minChanges(
            3, 21
        )
    }
}
