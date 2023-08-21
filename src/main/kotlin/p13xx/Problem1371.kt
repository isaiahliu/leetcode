package p13xx

import util.expect

fun main() {
    class Solution {
        fun findTheLongestSubstring(s: String): Int {
            val map = hashMapOf(0 to -1)

            var status = 0
            var result = 0

            s.forEachIndexed { index, c ->
                status = status xor when (c) {
                    'a' -> 1 shl 0
                    'e' -> 1 shl 1
                    'i' -> 1 shl 2
                    'o' -> 1 shl 3
                    'u' -> 1 shl 4
                    else -> 0
                }

                map[status]?.also {
                    result = result.coerceAtLeast(index - it)
                } ?: run {
                    map[status] = index
                }
            }

            return result
        }
    }

    expect {
        Solution().findTheLongestSubstring(
            "eleetminicoworoep"
        )
    }
}

