package p12xx

import util.expect

fun main() {
    class Solution {
        fun uniqueOccurrences(arr: IntArray): Boolean {
            return arr.toList().groupingBy { it }.eachCount().values.let { it.size == it.toSet().size }
        }
    }

    expect {
        Solution().uniqueOccurrences(
            intArrayOf(1, 2, 2, 1, 1, 3)
        )
    }
}
