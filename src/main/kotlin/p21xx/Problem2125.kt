package p21xx

import util.expect

fun main() {
    class Solution {
        fun numberOfBeams(bank: Array<String>): Int {
            var prevCount = 0
            var result = 0

            for (s in bank) {
                s.count { it == '1' }.takeIf { it > 0 }?.also {
                    result += prevCount * it
                    prevCount = it
                }
            }

            return result
        }
    }

    expect {
        Solution().numberOfBeams(
            arrayOf()
        )
    }
}