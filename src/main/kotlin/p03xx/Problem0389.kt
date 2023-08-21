package p03xx

import util.expect

fun main() {
    class Solution {
        fun findTheDifference(s: String, t: String): Char {
            var sum = 0
            t.forEach {
                sum += it - 'a'
            }
            s.forEach {
                sum -= it - 'a'
            }

            return 'a' + sum
        }
    }

    expect {
        Solution().findTheDifference(
            "abcd", "abcde"
        )
    }
}

