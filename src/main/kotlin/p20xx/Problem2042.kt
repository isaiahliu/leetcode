package p20xx

import util.expect

fun main() {
    class Solution {
        fun areNumbersAscending(s: String): Boolean {
            return s.split(" ").mapNotNull { it.toIntOrNull() }.fold(0) { max: Int?, b ->
                max?.takeIf { max < b }?.let { b }
            } != null
        }
    }

    expect {
        Solution().areNumbersAscending(
            "hello world 5 x 5"
        )
    }
}