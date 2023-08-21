package p24xx

import util.expect

fun main() {
    class Solution {
        fun maximumValue(strs: Array<String>): Int {
            return strs.maxOf { it.toIntOrNull() ?: it.length }
        }
    }

    expect {
        Solution().maximumValue(
            arrayOf()
        )
    }
}