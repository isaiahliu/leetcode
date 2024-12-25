package p30xx

import util.expect

fun main() {
    class Solution {
        fun isSubstringPresent(s: String): Boolean {
            val befores = Array(26) { hashSetOf<Int>() }
            val afters = Array(26) { hashSetOf<Int>() }

            s.forEachIndexed { index, ch ->
                s.getOrNull(index - 1)?.also {
                    if (it - 'a' in afters[ch - 'a']) {
                        return true
                    }

                    befores[ch - 'a'] += it - 'a'
                }
                s.getOrNull(index + 1)?.also {
                    if (it - 'a' in befores[ch - 'a']) {
                        return true
                    }

                    afters[ch - 'a'] += it - 'a'
                }
            }

            return false
        }
    }

    expect {
        Solution().isSubstringPresent(
            ""
        )
    }
}
