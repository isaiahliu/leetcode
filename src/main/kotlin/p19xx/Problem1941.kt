package p19xx

import util.expect

fun main() {
    class Solution {
        fun areOccurrencesEqual(s: String): Boolean {
            return s.groupingBy { it }.eachCount().values.distinct().size == 1
        }
    }

    expect {
        Solution().areOccurrencesEqual(
            ""
        )
    }
}