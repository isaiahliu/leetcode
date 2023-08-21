package p07xx

import util.expect

fun main() {
    class Solution {
        fun numJewelsInStones(jewels: String, stones: String): Int {
            return jewels.toSet().let { stones.count { stone -> stone in it } }
        }
    }

    expect {
        Solution().numJewelsInStones(
            "", ""
        )
    }
}