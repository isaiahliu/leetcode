package p15xx

import util.expect

fun main() {
    class Solution {
        fun restoreString(s: String, indices: IntArray): String {
            return indices.mapIndexed { index, i -> i to index }.sortedBy { it.first }.map { s[it.second] }
                .joinToString("")
        }
    }

    expect {
        Solution().restoreString(
            "", intArrayOf()
        )
    }
}

