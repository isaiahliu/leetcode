package p21xx

import util.expect

fun main() {
    class Solution {
        fun divideString(s: String, k: Int, fill: Char): Array<String> {
            return "\\w{1,${k}}".toRegex().findAll(s).map {
                it.value.padEnd(k, fill)
            }.toList().toTypedArray()
        }
    }

    expect {
        Solution().divideString(
            "abcdefghij", 1, ' '
        )
    }
}