package p04xx

import util.expect

fun main() {
    class Solution {
        fun longestPalindrome(s: String): Int {
            val set = hashSetOf<Char>()

            var result = 0

            s.forEach {
                if (it in set) {
                    set.remove(it)
                    result += 2
                } else {
                    set.add(it)
                }
            }

            if (set.isNotEmpty()) {
                result++
            }

            return result
        }
    }
    expect {
        Solution().longestPalindrome(
            ""
        )
    }
}


