package p08xx

import util.expect

fun main() {
    class Solution {
        fun uniqueLetterString(s: String): Int {
            val pos = Array(26) { arrayListOf<Int>() }

            s.forEachIndexed { index, c ->
                pos[c - 'A'].add(index)
            }

            var result = 0
            pos.forEach {
                it.forEachIndexed { index, p ->
                    val left = p - (it.getOrNull(index - 1) ?: -1)
                    val right = (it.getOrNull(index + 1) ?: s.length) - p

                    result += left * right
                }
            }

            return result
        }
    }

    expect {
        Solution().uniqueLetterString(
            "ABC"
        )
    }
}