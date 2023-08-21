package p19xx

import util.expect

fun main() {
    class Solution {
        fun minTimeToType(word: String): Int {
            var cur = 'a'

            var result = 0

            word.forEach {
                result++

                result += (cur.coerceAtLeast(it) - cur.coerceAtMost(it)).let { it.coerceAtMost(26 - it) }

                cur = it
            }

            return result
        }
    }

    expect {
        Solution().minTimeToType(
            "bza"
        )
    }
}