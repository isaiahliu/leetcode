package p16xx

import util.expect

fun main() {
    class Solution {
        fun minPartitions(n: String): Int {
            return n.max() - '0'
        }
    }

    expect {
        Solution().minPartitions(
            "32"
        )
    }
}

