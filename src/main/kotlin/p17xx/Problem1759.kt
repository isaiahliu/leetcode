package p17xx

import util.expect

fun main() {
    class Solution {
        fun countHomogenous(s: String): Int {
            var count = 0
            var pre = s[0]

            var result = 0L
            val m = 1000000007

            s.forEach {
                if (it == pre) {
                    count++
                } else {
                    count = 1
                    pre = it
                }

                result += count
                result %= m
            }

            return result.toInt()
        }
    }

    expect {
        Solution().countHomogenous(
            "abbcccaa"
        )
    }
}
