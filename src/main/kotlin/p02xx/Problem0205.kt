package p02xx

import util.expect

fun main() {
    class Solution {
        fun isIsomorphic(s: String, t: String): Boolean {
            val map = hashMapOf<Char, Char>()
            val used = hashSetOf<Char>()

            repeat(s.length) {
                val l = s[it]
                val r = t[it]

                if (l !in map && r !in used || map[l] == r) {
                    map[l] = r
                    used.add(r)
                } else {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().isIsomorphic(
            "badd", "baba"
        )
    }
}

