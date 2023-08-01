package p15xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun restoreString(s: String, indices: IntArray): String {
            return indices.mapIndexed { index, i -> i to index }.sortedBy { it.first }.map { s[it.second] }
                .joinToString("")
        }
    }

    measureTimeMillis {
        Solution().restoreString(
            "", intArrayOf()
        ).also { println(it) }
    }
}

