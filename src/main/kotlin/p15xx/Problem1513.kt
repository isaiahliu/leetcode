package p15xx

import util.expect

fun main() {
    class Solution {
        fun numSub(s: String): Int {
            var result = 0L
            var count = 0
            s.forEach {
                if (it == '1') {
                    count++
                    result += count
                } else {
                    count = 0
                }
            }

            return (result % 1000000007).toInt()
        }
    }

    expect {
        Solution().numSub(
            "0110111"
        )
    }
}

