package p19xx

import util.expect

fun main() {
    class Solution {
        fun countPalindromicSubsequence(s: String): Int {
            val left = IntArray(26)
            val right = IntArray(26)

            val result = hashSetOf<Int>()

            s.forEach {
                right[it - 'a']++
            }

            s.forEach {
                right[it - 'a']--

                val base = (it - 'a') * 26
                repeat(26) {
                    if (left[it] > 0 && right[it] > 0) {
                        result.add(base + it)
                    }
                }

                left[it - 'a']++
            }

            return result.size
        }
    }

    expect {
        Solution().countPalindromicSubsequence(
            "bbcbaba"
        )
    }
}