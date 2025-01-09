package p30xx

import util.expect

fun main() {
    class Solution {
        fun countKeyChanges(s: String): Int {
            val set = setOf(0, 'A' - 'a', 'a' - 'A')

            return (1 until s.length).count {
                s[it] - s[it - 1] !in set
            }
        }
    }

    expect {
        Solution().countKeyChanges(
            ""
        )
    }
}
