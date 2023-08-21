package p03xx

import util.expect

fun main() {
    class Solution {
        fun firstUniqChar(s: String): Int {
            val array = IntArray(26) { s.length }

            s.forEachIndexed { index, c ->
                when (array[c - 'a']) {
                    s.length -> {
                        array[c - 'a'] = index
                    }

                    Int.MAX_VALUE -> {
                    }

                    else -> {
                        array[c - 'a'] = Int.MAX_VALUE
                    }
                }
            }

            return array.min().takeIf { it < s.length } ?: -1
        }
    }

    expect {
        Solution().firstUniqChar(
            "leetcode"
        )
    }
}

