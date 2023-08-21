package p14xx

import util.expect

fun main() {
    class Solution {
        fun canConstruct(s: String, k: Int): Boolean {
            return k in s.groupingBy { it }.eachCount().values.count { it % 2 == 1 }..s.length
        }
    }

    expect {
        Solution().canConstruct(
            "annabelle", 3
        )
    }
}

